package or.sopt.assignment.global.handler;

import or.sopt.assignment.global.errorStatus.ErrorStatus;

public class CustomException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }
}
