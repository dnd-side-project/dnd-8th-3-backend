package d83t.bpmbackend.domain.aggregate.community.entity;

import d83t.bpmbackend.base.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BoastImage")
public class BodyShapeImage extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BodyShape bodyShape;

    @Column(name = "filename", nullable = false)
    private String originFileName;

    @Column(name = "path", nullable = false)
    private String storagePathName;
}
