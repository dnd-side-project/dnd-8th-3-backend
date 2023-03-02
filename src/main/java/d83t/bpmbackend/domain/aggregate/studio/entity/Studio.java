package d83t.bpmbackend.domain.aggregate.studio.entity;

import d83t.bpmbackend.base.entity.DateEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "studio")
public class Studio extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String firstTag;

    @Column
    private String secondTag;

    // TODO: recommends 추가

    @Column
    private String phone;

    @Column
    private String sns;

    @Column
    private String openHours;

    @Column
    private String price;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudioImage> images;

    @Column
    private String content;

    @Column(columnDefinition = "double default 0.0")
    private double rating;

    @Column(columnDefinition = "int default 0")
    private int reviewCount;

    @Column(columnDefinition = "int default 0")
    private int scrapCount;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Studio(String name, String address, double latitude, double longitude, String firstTag, String secondTag, String phone, String sns, String openHours, String price, List<StudioImage> images, String content, double rating, int reviewCount, int scrapCount) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.firstTag = firstTag;
        this.secondTag = secondTag;
        this.phone = phone;
        this.sns = sns;
        this.openHours = openHours;
        this.price = price;
        this.images = images;
        this.content = content;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.scrapCount = scrapCount;
    }

    public void addStudioImage(StudioImage studioImage) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(studioImage);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        if (review.getRating() != 0.0) {
            double avg = ((this.rating * reviewCount) + review.getRating()) / (reviewCount + 1);
            this.rating = avg;
        }
        this.reviewCount += 1;
        review.setStudio(this);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        if (review.getRating() != 0.0) {
            double avg = ((this.rating * reviewCount) - review.getRating()) / (reviewCount - 1);
            this.rating = avg;
        }
        this.reviewCount -= 1;
        review.setStudio(null);
    }
}
