package d83t.bpmbackend.domain.aggregate.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Schema(description = "스케줄 등록 응답 DTO")
public class ScheduleResponse {
    @Schema(defaultValue = "studio name")
    private String studioName;
    @Schema(defaultValue = "2001-01-01")
    private LocalDate date;
    @Schema(defaultValue = "00:00:00")
    private LocalTime time;
    @Schema(defaultValue = "스케줄 아자아자 확인")
    private String memo;
}
