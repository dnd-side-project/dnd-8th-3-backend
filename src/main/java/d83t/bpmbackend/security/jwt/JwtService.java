package d83t.bpmbackend.security.jwt;

import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.domain.aggregate.profile.service.UserServiceDetail;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@Getter
@AllArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;

    private final UserServiceDetail userServiceDetail;

    private Key getSignKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(jwtConfig.getKey()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.after(new Date());
    }

    public String getNickname(String token) {
        return extractAllClaims(token).get("nickname", String.class);
    }

    public String createToken(String nickname) {
        Claims claims = Jwts.claims();
        claims.put("nickname", nickname);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiry() * 1000))
                .signWith(getSignKey(jwtConfig.getKey()))
                .compact();
    }

    public Boolean validateToken(String token) {
        return isTokenExpired(token);
    }

    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = userServiceDetail.loadUserByUsername(getNickname(jwtToken));
        User user = (User)userDetails;
        return new UsernamePasswordAuthenticationToken(user, "", userDetails.getAuthorities());
    }

}
