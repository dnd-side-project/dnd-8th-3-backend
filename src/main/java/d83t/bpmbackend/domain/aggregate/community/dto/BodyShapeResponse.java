package d83t.bpmbackend.domain.aggregate.community.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
public class BodyShapeResponse {
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private List<String> filesPath;

    private Author author;

    @Builder
    @Getter
    public static class Author{
        private String nickname;
        private String profilePath;
    }

    @Builder
    @Getter
    public static class SigneBodyShapes{
        BodyShapeResponse bodyShapeArticle;
    }

    @Builder
    @Getter
    public static class MultiBodyShapes{
        List<BodyShapeResponse> bodyShapeArticles;
        Integer bodyShapeCount;
    }
}
