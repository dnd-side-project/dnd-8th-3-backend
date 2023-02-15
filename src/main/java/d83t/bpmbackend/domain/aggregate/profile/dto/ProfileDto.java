package d83t.bpmbackend.domain.aggregate.profile.dto;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ProfileDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String bio;

    private String imageName;
    private String imagePath;

    @Getter
    @JsonTypeName("profile")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Single {
        private String nickname;
        private String bio;

        public Single(ProfileDto profile){
            this.nickname = profile.getNickname();
            this.bio = profile.getBio();
        }
    }

}
