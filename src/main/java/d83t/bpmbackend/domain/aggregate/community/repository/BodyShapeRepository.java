package d83t.bpmbackend.domain.aggregate.community.repository;

import d83t.bpmbackend.domain.aggregate.community.entity.BodyShape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyShapeRepository extends JpaRepository<BodyShape, Long> {
}
