package d83t.bpmbackend.domain.aggregate.studio.entity;

import d83t.bpmbackend.base.entity.DateEntity;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Profile author;

    @Column
    private double rating;

    // TODO: recommends 필드 추가

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> images;

    @Column
    private String content;

    @Column(columnDefinition = "int default 0")
    private int likeCount;

    @Builder
    public Review(Studio studio, Profile author, double rating, List<ReviewImage> images, String content, int likeCount) {
        this.studio = studio;
        this.author = author;
        this.rating = rating;
        this.images = images;
        this.content = content;
        this.likeCount = likeCount;
    }

    public void addReviewImage(ReviewImage image) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
