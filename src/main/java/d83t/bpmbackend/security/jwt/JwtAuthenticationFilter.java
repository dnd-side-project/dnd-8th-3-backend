package d83t.bpmbackend.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";

    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = getToken(request.getHeader(HEADER));
        if(token.isPresent() && jwtService.validateToken(token.get())){
            String jwt = token.get();
            Authentication auth = jwtService.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request,response);
    }

    private Optional<String> getToken(String header) {
        if(header == null){
            return Optional.empty();
        }else{
            String[] s = header.split(" ");
            if(s.length < 2){
                return Optional.empty();
            }else{
                return Optional.ofNullable(s[1]);
            }
        }
    }
}
