package d83t.bpmbackend.domain.aggregate.community.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
public class BodyShapeResponse {
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private List<MultipartFile> files;

    private Author author;

    @Builder
    @Getter
    public static class Author{
        private String nickname;
        private MultipartFile profileImage;
    }
}
