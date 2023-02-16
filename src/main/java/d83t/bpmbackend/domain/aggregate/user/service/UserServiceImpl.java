package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.domain.aggregate.profile.service.ProfileImageService;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final ProfileImageService profileImageService;
    private final UserRepository userRepository;

    @Override
    public ProfileResponse signUp(Long kakaoId, ProfileRequest profileRequest, MultipartFile file) {
        ProfileDto profileDto = profileImageService.setUploadFile(profileRequest,file);

        Profile profile = profileImageService.convertProfileDto(profileDto);
        User user = User.builder()
                .kakaoId(kakaoId)
                .profile(profile)
                .build();
        userRepository.save(user);
        return ProfileResponse.builder()
                .nickname(profileDto.getNickname())
                .bio(profileRequest.getBio())
                .build();
    }
}
