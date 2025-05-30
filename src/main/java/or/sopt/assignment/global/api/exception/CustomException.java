package or.sopt.assignment.global.api.exception;

import lombok.Getter;
import or.sopt.assignment.global.api.exception.status.ErrorStatus;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

}
