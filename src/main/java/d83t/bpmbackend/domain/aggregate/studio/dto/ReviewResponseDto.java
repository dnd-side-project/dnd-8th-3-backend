package d83t.bpmbackend.domain.aggregate.studio.dto;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.domain.aggregate.studio.entity.Review;
import d83t.bpmbackend.domain.aggregate.studio.entity.ReviewImage;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(description = "리뷰 응답 DTO")
public class ReviewResponseDto {
    private Long id;
    private StudioDto studio;
    private AuthorDto author;
    private double rating;
    private List<String> filesPath;
    private String content;
    private int likeCount;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


    @Builder
    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.content = review.getContent();
        this.likeCount = review.getLikeCount();
        this.createdAt = review.getCreatedDate();
        this.updatedAt = review.getModifiedDate();

        Studio studio = review.getStudio();
        this.studio = new StudioDto(studio.getId(), studio.getName(), studio.getRating(), studio.getContent());
        Profile profile = review.getAuthor();
        this.author = new AuthorDto(profile.getNickName(), profile.getStoragePathName());

        List<String> filePaths = new ArrayList<>();
        for (ReviewImage image : review.getImages()) {
            filePaths.add(image.getStoragePathName());
        }
        this.filesPath = filePaths;
    }

    @Builder
    @Getter
    public static class StudioDto {
        private Long id;
        private String name;
        private double rating;
        private String content;
    }

    @Builder
    @Getter
    public static class AuthorDto {
        private String nickname;
        private String profilePath;
    }

}
