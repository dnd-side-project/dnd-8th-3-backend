package d83t.bpmbackend.domain.aggregate.studio.dto;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.entity.StudioImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(description = "스튜디오 응답 DTO")
public class StudioDto {
    private Long id;
    private String name;
    private Location location;
    private int phone;
    private String sns;
    private String openHours;
    private String price;
    private List<String> filesPath;
    private String content;
    private double rating;
    private int reviewCount;
    private int scrapCount;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public StudioDto(Studio studio) {
        this.id = studio.getId();
        this.name = studio.getName();
        this.location = studio.getLocation();
        this.phone = studio.getPhone();
        this.sns = studio.getSns();
        this.openHours = studio.getOpenHours();
        this.price = studio.getPrice();

        List<String> filePaths = new ArrayList<>();
        for (StudioImage images : studio.getImages()) {
            filePaths.add(images.getStoragePathName());
        }
        this.filesPath = filePaths;

        this.content = studio.getContent();
        this.rating = studio.getRating();
        this.reviewCount = studio.getReviewCount();
        this.scrapCount = studio.getScrapCount();
        this.createdAt = studio.getCreatedDate();
        this.updatedAt = studio.getModifiedDate();
    }
}
