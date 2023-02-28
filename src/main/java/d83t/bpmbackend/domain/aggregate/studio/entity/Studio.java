package d83t.bpmbackend.domain.aggregate.studio.entity;

import d83t.bpmbackend.base.entity.DateEntity;
import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Studio extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // TODO: recommends 필드 추가

    @Column
    private int phone;

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

    @Column
    private double rating;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int reviewCount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int scrapCount;

    @Builder
    public Studio(String name, Location location, int phone, String sns, String openHours, String price) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.sns = sns;
        this.openHours = openHours;
        this.price = price;
    }

    public void addStudioImage(StudioImage studioImage) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(studioImage);
    }
}
