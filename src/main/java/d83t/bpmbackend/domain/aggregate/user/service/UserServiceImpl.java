package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
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
    public ProfileDto signUp(Long kakaoId, ProfileDto profileDto, MultipartFile file) {
        profileImageService.setUploadFile(profileDto,file);
        Profile profile = profileImageService.convertProfileDto(profileDto);
        User user = User.builder()
                .kakaoId(kakaoId)
                .profile(profile)
                .build();
        userRepository.save(user);
        return profileDto;
    }
}
