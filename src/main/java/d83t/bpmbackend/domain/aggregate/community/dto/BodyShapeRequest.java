package d83t.bpmbackend.domain.aggregate.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class BodyShapeRequest {

    @Schema(description = "게시글의 본문", defaultValue = "게시글 본문입니다.")
    private String content;

}
