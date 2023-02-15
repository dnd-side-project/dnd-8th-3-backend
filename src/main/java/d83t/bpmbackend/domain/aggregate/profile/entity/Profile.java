package d83t.bpmbackend.domain.aggregate.profile.entity;

import d83t.bpmbackend.domain.aggregate.user.entity.User;
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
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "filename", nullable = false)
    private String originFileName;

    @Column(name = "path", nullable = false)
    private String storagePathName;

    @OneToOne(mappedBy = "profile")
    private User user;

}
