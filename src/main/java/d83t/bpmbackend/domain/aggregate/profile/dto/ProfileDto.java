package d83t.bpmbackend.domain.aggregate.profile.dto;

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

}
