package d83t.bpmbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .version("v1.0.0")
                .title("BPM")
                .description("Body Profile Manager")
                .summary("dnd8기-3조 BPM 프로젝트 입니다."));
    }
}
