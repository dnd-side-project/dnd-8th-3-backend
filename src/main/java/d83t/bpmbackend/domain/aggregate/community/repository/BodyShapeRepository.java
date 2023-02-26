package d83t.bpmbackend.domain.aggregate.community.repository;

import d83t.bpmbackend.domain.aggregate.community.entity.BodyShape;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BodyShapeRepository extends JpaRepository<BodyShape, Long> {

    @Query("SELECT a FROM BodyShape a ORDER BY a.createdDate DESC")
    List<BodyShape> findByAll(Pageable pageable);
}
