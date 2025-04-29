package or.sopt.assignment.global.status;

import org.springframework.http.HttpStatus;

public enum SuccessStatus {

    _CREATED_SUCCESS(HttpStatus.CREATED,"201","정상적으로 생성되었습니다");


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

    SuccessStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
