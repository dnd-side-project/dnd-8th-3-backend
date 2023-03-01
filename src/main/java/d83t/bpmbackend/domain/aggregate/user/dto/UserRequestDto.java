package d83t.bpmbackend.domain.aggregate.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오 유저 확인API 요청 DTO")
public class UserRequestDto {

    @Schema(description = "kakao uid", defaultValue = "9873721")
    @NotBlank
    private Long kakaoId;
}
