package d83t.bpmbackend.domain.aggregate.profile.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("profile")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@Schema(description = "카카오 로그인 API 응답 DTO")
public class ProfileResponse {
    private String nickname;
    private String bio;
    private String token;
    private String image;

}
