package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ProfileResponse signUp(ProfileRequest profileRequest, MultipartFile file);

    ProfileResponse verification(UserRequestDto userRequestDto);
}
