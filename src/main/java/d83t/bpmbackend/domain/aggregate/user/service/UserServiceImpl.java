package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.domain.aggregate.profile.repository.ProfileRepository;
import d83t.bpmbackend.domain.aggregate.profile.service.ProfileImageService;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.repository.UserRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import d83t.bpmbackend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ProfileImageService profileImageService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    private final JwtService jwtService;

    @Override
    public ProfileResponse signUp(ProfileRequest profileRequest, MultipartFile file) {
        Optional<User> findUser = userRepository.findByKakaoId(profileRequest.getKakaoId());
        if(findUser.isPresent()){
            throw new CustomException(Error.USER_ALREADY_EXITS);
        }
        //닉네임 중복여부
        if(profileRepository.findByNickName(profileRequest.getNickname()).isPresent()){
            throw new CustomException(Error.USER_NICKNAME_ALREADY_EXITS);
        }
        ProfileDto profileDto = profileImageService.setUploadFile(profileRequest, file);

        Profile profile = profileImageService.convertProfileDto(profileDto);
        User user = User.builder()
                .kakaoId(profileRequest.getKakaoId())
                .profile(profile)
                .build();
        userRepository.save(user);
        return ProfileResponse.builder()
                .nickname(profileDto.getNickname())
                .bio(profileDto.getBio())
                .image(profileDto.getImagePath())
                .token(jwtService.createToken(profileRequest.getNickname()))
                .build();
    }

    @Override
    public ProfileResponse verification(UserRequestDto userRequestDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByKakaoId(userRequestDto.getKakaoId()).orElseThrow(
                () -> new CustomException(Error.NOT_FOUND_USER_ID))
        );
        Profile userProfile = user.get().getProfile();
        return ProfileResponse.builder()
                .nickname(userProfile.getNickName())
                .bio(userProfile.getBio())
                .token(jwtService.createToken(userProfile.getNickName()))
                .build();
    }
}
