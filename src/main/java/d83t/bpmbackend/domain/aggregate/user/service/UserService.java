package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleDto;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ProfileResponse signUp(Long kakaoId, ProfileRequest profileRequest, MultipartFile file);

    ProfileResponse verification(Long kakaoId);

    ScheduleResponse registerSchedule(User user, ScheduleDto scheduleDto);

    ScheduleResponse getSchedule(User user);
}
