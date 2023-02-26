package d83t.bpmbackend.s3;

import org.springframework.web.multipart.MultipartFile;

public interface S3UploaderService {

    void putS3(MultipartFile uploadFile, String path, String fileName);
}
