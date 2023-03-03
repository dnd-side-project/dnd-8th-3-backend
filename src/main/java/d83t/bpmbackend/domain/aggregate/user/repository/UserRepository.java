package d83t.bpmbackend.domain.aggregate.user.repository;

import d83t.bpmbackend.domain.aggregate.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKakaoId(long kakaoId);
}
