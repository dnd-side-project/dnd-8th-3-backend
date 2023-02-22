package d83t.bpmbackend.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtConfig {

    @Value("${bpm.token.expiry}")
    private Long expiry;

    @Value("${bpm.token.key}")
    private String key;
}
