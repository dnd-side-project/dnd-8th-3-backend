package d83t.bpmbackend.domain.aggregate.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
public class BodyShapeResponse {
    @Schema(description = "내 눈바디 게시글의 id", defaultValue = "1")
    private Long id;
    @Schema(description = "내 눈바디 게시글의 본문", defaultValue = "본문입니다.")
    private String content;

    @Schema(description = "내 눈바디 게시글의 생성 시간")
    private ZonedDateTime createdAt;
    @Schema(description = "내 눈바디 게시글의 갱신 시간")
    private ZonedDateTime updatedAt;

    @Schema(description = "내 눈바디 게시글의 등록된 이미지 경로들", defaultValue = "https://s3이미지경로")
    private List<String> filesPath;

    private Author author;

    @Builder
    @Getter
    public static class Author{
        @Schema(description = "내 눈바디 게시글의 작성자 닉네임", defaultValue = "nickname")
        private String nickname;
        @Schema(description = "내 눈바디 게시글의 작성자 프로필 경로",  defaultValue = "https://s3이미지경로")
        private String profilePath;
    }

    @Builder
    @Getter
    public static class SingleBodyShape{
        BodyShapeResponse bodyShapeArticle;
    }

    @Builder
    @Getter
    public static class MultiBodyShapes{
        List<BodyShapeResponse> bodyShapeArticles;
        Integer bodyShapeCount;
    }
}
