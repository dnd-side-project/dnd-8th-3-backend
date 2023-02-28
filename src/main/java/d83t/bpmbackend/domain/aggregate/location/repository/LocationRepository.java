package d83t.bpmbackend.domain.aggregate.location.repository;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsLocationByLatitudeAndLongitude(int latitude, int longitude);
}