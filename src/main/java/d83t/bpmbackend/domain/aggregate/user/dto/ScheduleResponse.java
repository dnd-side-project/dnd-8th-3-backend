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
    private String studioName;
    private LocalDate date;
    private LocalTime time;
    private String memo;
}
