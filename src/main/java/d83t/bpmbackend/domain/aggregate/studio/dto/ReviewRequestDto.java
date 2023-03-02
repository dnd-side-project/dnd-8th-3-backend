package d83t.bpmbackend.domain.aggregate.studio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "리뷰 작성 요청 DTO")
public class ReviewRequestDto {
    private double rating;
    private List<String> recommends;
    private String content;

    @Builder
    public ReviewRequestDto(double rating, List<String> recommends, String content) {
        this.rating = rating;
        this.recommends = recommends;
        this.content = content;
    }
}
