package d83t.bpmbackend.domain.aggregate.studio.repository;

import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    Optional<Studio> findByName(String name);

    @Query("SELECT s FROM Studio s WHERE s.name LIKE %?1%")
    List<Studio> searchStudioNames(String query);

    @Query("SELECT studio FROM Studio studio ORDER BY studio.createdDate DESC")
    List<Studio> findByAll(Pageable pageable);
}
