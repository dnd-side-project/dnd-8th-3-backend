package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.domain.aggregate.profile.repository.ProfileRepository;
import d83t.bpmbackend.domain.aggregate.profile.service.ProfileImageService;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleDto;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import d83t.bpmbackend.domain.aggregate.user.entity.Schedule;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.repository.ScheduleRepository;
import d83t.bpmbackend.domain.aggregate.user.repository.UserRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import d83t.bpmbackend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ProfileImageService profileImageService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final StudioRepository studioRepository;
    private final ScheduleRepository scheduleRepository;

    private final JwtService jwtService;

    @Override
    public ProfileResponse signUp(ProfileRequest profileRequest, MultipartFile file) {
        Optional<User> findUser = userRepository.findByKakaoId(profileRequest.getKakaoId());
        if(findUser.isPresent()){
            throw new CustomException(Error.USER_ALREADY_EXITS);
        }
        //닉네임 중복여부
        if (profileRepository.findByNickName(profileRequest.getNickname()).isPresent()) {
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
                .image(userProfile.getStoragePathName())
                .build();
    }


    @Override
    public ScheduleResponse registerSchedule(User user, ScheduleDto scheduleDto) {
        Optional<Studio> findStudio = studioRepository.findByName(scheduleDto.getStudioName());
        if (findStudio.isEmpty()) {
            throw new CustomException(Error.NOT_FOUND_STUDIO);
        }
        Studio studio = findStudio.get();

        Schedule schedule = Schedule.builder()
                .studio(studio)
                .user(user)
                .date(convertDateFormat(scheduleDto.getDate()))
                .time(convertTimeFormat(scheduleDto.getTime()))
                .memo(scheduleDto.getMemo())
                .build();
        scheduleRepository.save(schedule);
        return ScheduleResponse.builder()
                .studioName(scheduleDto.getStudioName())
                .time(schedule.getTime())
                .date(schedule.getDate())
                .memo(schedule.getMemo())
                .build();
    }


    private LocalDate convertDateFormat(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    private LocalTime convertTimeFormat(String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(time, dateTimeFormatter);
    }
}
