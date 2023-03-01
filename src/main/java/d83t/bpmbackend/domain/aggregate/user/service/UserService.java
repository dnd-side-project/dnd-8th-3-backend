package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleDto;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ProfileResponse signUp(ProfileRequest profileRequest, MultipartFile file);
    ScheduleResponse registerSchedule(User user, ScheduleDto scheduleDto);
    ScheduleResponse getSchedule(User user);
    ProfileResponse verification(UserRequestDto userRequestDto);
}
