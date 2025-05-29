package or.sopt.assignment.global.api.exception.status;

import org.springframework.http.HttpStatus;

public class ErrorDTO {

    private HttpStatus httpStatus;
    private String code;
    private String message;


    public ErrorDTO(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
