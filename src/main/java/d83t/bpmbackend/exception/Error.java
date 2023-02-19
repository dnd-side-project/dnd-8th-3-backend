package d83t.bpmbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {
    DUPLICATE_USER_NICK_NAME("duplicate user nickname", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;

    Error(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

}
