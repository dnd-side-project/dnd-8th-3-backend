package d83t.bpmbackend.domain.aggregate.community.repository;

import d83t.bpmbackend.domain.aggregate.community.entity.BodyShape;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BodyShapeRepository extends JpaRepository<BodyShape, Long> {

    @Query("SELECT a FROM BodyShape a WHERE a.author.nickName= :nickName ORDER BY a.createdDate DESC")
    List<BodyShape> findByNickName(Pageable pageable, @Param("nickName") String nickName);
}
