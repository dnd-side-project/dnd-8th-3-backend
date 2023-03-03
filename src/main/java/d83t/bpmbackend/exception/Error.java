package d83t.bpmbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {
    FILE_TRANSFER_FAIL("ailed to transfer file", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_USER_ID("kakao user not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXITS("user already exits", HttpStatus.CONFLICT),
    USER_ALREADY_REGISTER_SCHEDULE("user already register schedule", HttpStatus.BAD_REQUEST),
    USER_NICKNAME_ALREADY_EXITS("user nickname already exits", HttpStatus.CONFLICT),
    S3_UPLOAD_FAIL("upload fail", HttpStatus.INTERNAL_SERVER_ERROR),
    S3_GET_FILE_FAIL("fail to get file", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATE_USER_NICK_NAME("duplicate user nickname", HttpStatus.CONFLICT),
    NOT_FOUND_BODY_SHAPE("bodyshape article not found",HttpStatus.NOT_FOUND),
    FILE_SIZE_MAX("a Maximum of 5 files can Come in", HttpStatus.BAD_REQUEST),
    NOT_FOUND_LOCATION("location not found", HttpStatus.NOT_FOUND),
    NOT_FOUND_STUDIO("studio not found", HttpStatus.NOT_FOUND),
    NOT_FOUND_SCHEDULE("schedule not found",HttpStatus.NO_CONTENT),
    STUDIO_ALREADY_EXISTS("studio already exists", HttpStatus.CONFLICT),
    NOT_FOUND_REVIEW("review not found", HttpStatus.NOT_FOUND),
    NOT_FOUND_LIKE("like not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
