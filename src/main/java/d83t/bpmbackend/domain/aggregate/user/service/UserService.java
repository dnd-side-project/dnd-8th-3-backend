package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;

public interface UserService {
    ProfileResponse signUp(ProfileRequest profileRequest);

    ProfileResponse verification(UserRequestDto userRequestDto);
}
