package d83t.bpmbackend.domain.aggregate.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오 로그인 API 요청 DTO")
public class ProfileRequest {

    @Schema(description = "카카오 uid", defaultValue = "5834L")
    @NotBlank
    private Long kakaoId;

    @Schema(description = "사용자가 등록한 프로필 닉네임", defaultValue = "nickname")
    @NotBlank
    private String nickname;

    @Schema(description = "사용자가 등록한 한줄 소개", defaultValue = "한줄 소개 입니다.")
    private String bio;

}
