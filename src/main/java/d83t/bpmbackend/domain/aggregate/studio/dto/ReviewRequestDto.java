package d83t.bpmbackend.domain.aggregate.studio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "리뷰 작성 요청 DTO")
public class ReviewRequestDto {
    private double rating;
    private String content;

    @Builder
    public ReviewRequestDto(double rating, String content) {
        this.rating = rating;
        this.content = content;
    }
}
