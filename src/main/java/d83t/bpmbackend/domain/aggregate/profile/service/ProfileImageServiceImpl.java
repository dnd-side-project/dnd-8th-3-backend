package d83t.bpmbackend.domain.aggregate.profile.service;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {

    @Override
    public void setUploadFile(ProfileDto profileDto, MultipartFile file) {
        String path = getUploadPath();
        String newName = createNewFileName(file.getOriginalFilename());
        String filePath = path + newName;
        profileDto.setImageName(newName);
        profileDto.setImagePath(filePath);
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e){
            e.printStackTrace();
        }
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

    private String getUploadPath() {
        String path = new File("").getAbsolutePath() + "\\" + "images\\";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }

    private String createNewFileName(String originalName){
        return UUID.randomUUID() + "." + originalName.substring(originalName.lastIndexOf("."));
    }
}
