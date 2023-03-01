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
public class ScheduleDto {
    private String studioName;
    private String date;
    private String time;
    private String memo;
}
