package d83t.bpmbackend.config;

import d83t.bpmbackend.domain.aggregate.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithAuthUserSecurityContextFactory implements WithSecurityContextFactory<WithAuthUser>{

    @Override
    public SecurityContext createSecurityContext(WithAuthUser annotation) {
        Long id = annotation.kakaoId();

        User userAuth = User.builder()
                .kakaoId(id)
                .build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userAuth,"",null);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        return context;

    }
}
