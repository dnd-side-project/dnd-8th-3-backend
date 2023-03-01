package d83t.bpmbackend.domain.aggregate.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Schema(description = "스케줄 등록 요청 DTO")
public class ScheduleRequest {
    @Schema(description = "스튜디오 이름", defaultValue = "바디프로필 스튜디오")
    private String studioName;
    @Schema(description = "날짜", defaultValue = "2022-01-01")
    private String date;
    @Schema(description = "시간", defaultValue = "00:00:00")
    private String time;
    @Schema(description = "메모", defaultValue = "이번엔 열심히 아자아자 화이팅!")
    private String memo;
}
