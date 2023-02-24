package d83t.bpmbackend.domain.aggregate.profile.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import d83t.bpmbackend.s3.S3UploaderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
        if (env.equals("local")) {
            this.fileDir = getUploadPath();
        } else if (env.equals("prod")) {
            this.fileDir = this.profilePath;
        }
    }

    @Override
    public ProfileDto setUploadFile(ProfileRequest profileRequest, MultipartFile file) {
        ProfileDto profileDto = ProfileDto.builder()
                .nickname(profileRequest.getNickname())
                .bio(profileRequest.getBio())
                .build();
        String newName = createNewFileName(file.getOriginalFilename());
        String filePath = fileDir + newName;
        profileDto.setImageName(newName);
        profileDto.setImagePath(filePath);
        if (env.equals("prod")) {
            uploaderService.putS3(file, profilePath, newName);
        } else if (env.equals("local")) {
            try {
                File localFile = new File(filePath);
                file.transferTo(localFile);
                removeNewFile(localFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return profileDto;
    }

    @Override
    public Profile convertProfileDto(ProfileDto profileDto) {
        return Profile.builder()
                .bio(profileDto.getBio())
                .nickName(profileDto.getNickname())
                .originFileName(profileDto.getImageName())
                .storagePathName(profileDto.getImagePath())
                .build();
    }


    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("file delete success");
            return;
        }
        log.info("file delete fail");
    }

    private String getUploadPath() {
        String path = new File("").getAbsolutePath() + "/" + "images/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }

    private String createNewFileName(String originalName) {
        return UUID.randomUUID() + "." + originalName.substring(originalName.lastIndexOf("."));
    }
}
