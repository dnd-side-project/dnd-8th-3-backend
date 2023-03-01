package d83t.bpmbackend.domain.aggregate.studio.repository;

import d83t.bpmbackend.domain.aggregate.studio.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
