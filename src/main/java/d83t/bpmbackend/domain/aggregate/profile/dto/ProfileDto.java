package d83t.bpmbackend.domain.aggregate.profile.dto;

import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ProfileDto {

    private String nickname;
    private String bio;
    private String imageName;
    private String imagePath;

    public Profile toEntity(){
        return Profile.builder()
                .bio(this.getBio())
                .nickName(this.getNickname())
                .originFileName(this.getImageName())
                .storagePathName(this.getImagePath())
                .build();
    }
}
