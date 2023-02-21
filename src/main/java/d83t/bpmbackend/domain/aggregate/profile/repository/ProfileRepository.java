package d83t.bpmbackend.domain.aggregate.profile.repository;

import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByNickName(String nickname);
}
