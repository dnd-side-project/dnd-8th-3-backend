package d83t.bpmbackend.domain.aggregate.studio.entity;

import d83t.bpmbackend.base.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class StudioImage extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @Column(name = "filename", nullable = false)
    private String originFileName;

    @Column(name = "path", nullable = false)
    private String storagePathName;

    @Builder
    public StudioImage(String originFileName, String storagePathName) {
        this.originFileName = originFileName;
        this.storagePathName = storagePathName;
    }
}
