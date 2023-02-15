package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ProfileDto signUp(Long kakaoId, ProfileDto profileDto, MultipartFile file);
}
