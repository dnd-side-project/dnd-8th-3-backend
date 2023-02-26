package d83t.bpmbackend.domain.aggregate.community.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
@JsonTypeName("boast")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
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
}
