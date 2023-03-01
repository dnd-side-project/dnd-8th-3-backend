package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleRequest;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ProfileResponse signUp(ProfileRequest profileRequest, MultipartFile file);
    ScheduleResponse registerSchedule(User user, ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedule(User user);
    ProfileResponse verification(UserRequestDto userRequestDto);
}
