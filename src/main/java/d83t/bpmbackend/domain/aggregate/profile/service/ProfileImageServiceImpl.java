package d83t.bpmbackend.domain.aggregate.profile.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import d83t.bpmbackend.s3.S3UploaderService;
import d83t.bpmbackend.utils.FileUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileImageServiceImpl implements ProfileImageService {

    private final S3UploaderService uploaderService;

    @Value("${bpm.s3.bucket.profile.path}")
    private String profilePath;

    @Value("${spring.environment}")
    private String env;

    private String fileDir;

    @PostConstruct
    private void init() {
        this.fileDir = env.equals("local") ? FileUtils.getUploadPath() : this.profilePath;
    }

    @Override
    public ProfileDto createProfileDto(ProfileRequest profileRequest, MultipartFile file) {
        return setUploadFile(profileRequest, file);
    }

    private ProfileDto setUploadFile(ProfileRequest profileRequest, MultipartFile file) {
        String newName = FileUtils.createNewFileName(file.getOriginalFilename());
        String filePath = fileDir + newName;
        String imagePath = env.equals("prod") ? uploaderService.putS3(file, profilePath, newName) : filePath;

        if(env.equals("local")) {
            try {
                File localFile = new File(filePath);
                file.transferTo(localFile);
                FileUtils.removeNewFile(localFile);
            } catch (IOException e) {
                log.error("Failed to transfer file: {}", e.getMessage());
                throw new CustomException(Error.FILE_TRANSFER_FAIL);
            }
        }

        return ProfileDto.builder()
                .nickname(profileRequest.getNickname())
                .bio(profileRequest.getBio())
                .imageName(newName)
                .imagePath(imagePath)
                .build();
    }
}
