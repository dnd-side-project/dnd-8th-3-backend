package d83t.bpmbackend.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3UploaderServiceImpl implements S3UploaderService {

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucket;

    public void putS3(MultipartFile uploadFile, String path, String fileName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(uploadFile.getContentType());
        metadata.setContentLength(uploadFile.getSize());
        fileName = path + fileName;
        try {
            PutObjectRequest request = new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(), metadata);
            s3Client.putObject(request);
        } catch (IOException e) {
            throw new CustomException(Error.S3_UPLOAD_FAIL);
        }
    }

}
