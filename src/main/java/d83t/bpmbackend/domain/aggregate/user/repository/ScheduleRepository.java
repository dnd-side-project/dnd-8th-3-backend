package d83t.bpmbackend.domain.aggregate.user.repository;

import d83t.bpmbackend.domain.aggregate.user.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByUserId(long userId);
}
