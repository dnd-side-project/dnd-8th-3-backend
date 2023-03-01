package d83t.bpmbackend.domain.aggregate.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오 로그인 API 응답 DTO")
public class ProfileResponse {
    @Schema(description = "프로필 닉네임", defaultValue = "닉네임입니다.")
    private String nickname;
    @Schema(description = "한줄 소개", defaultValue = "한줄 소개입니다.")
    private String bio;
    @Schema(description = "토큰", defaultValue = "0xbpdkjghjnxclklj123~")
    private String token;
    @Schema(description = "이미지 경로", defaultValue = "https://이미지경로")
    private String image;

}
