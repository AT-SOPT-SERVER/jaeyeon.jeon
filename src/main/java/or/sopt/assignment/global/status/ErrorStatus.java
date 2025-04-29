package or.sopt.assignment.global.status;

import org.springframework.http.HttpStatus;

public enum ErrorStatus {

    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","야 이놈아");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
