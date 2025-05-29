package or.sopt.assignment.global.exception;

import lombok.Getter;
import or.sopt.assignment.global.api.status.ErrorStatus;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

}
