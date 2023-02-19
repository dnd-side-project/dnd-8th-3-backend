package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ProfileResponse signUp(Long kakaoId, ProfileRequest profileRequest, MultipartFile file);
}