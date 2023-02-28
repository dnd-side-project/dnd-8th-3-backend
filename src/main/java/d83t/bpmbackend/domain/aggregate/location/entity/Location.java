package d83t.bpmbackend.domain.aggregate.location.entity;

import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "location", fetch = FetchType.LAZY)
    private Studio studio;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int latitude;

    @Column(nullable = false)
    private int longitude;

    @Builder
    public Location(String address, int latitude, int longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
