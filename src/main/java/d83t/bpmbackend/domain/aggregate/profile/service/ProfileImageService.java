package d83t.bpmbackend.domain.aggregate.profile.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {

    void setUploadFile(ProfileDto profileDto, MultipartFile file);

    Profile convertProfileDto(ProfileDto profileDto);
}
