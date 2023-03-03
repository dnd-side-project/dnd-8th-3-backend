package d83t.bpmbackend.domain.aggregate.profile.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {
    ProfileDto createProfileDto(ProfileRequest profileRequest, MultipartFile file);
}
