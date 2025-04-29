package or.sopt.assignment.global.status;

import org.springframework.http.HttpStatus;

public enum SuccessStatus {

    _READ_SUCCESS(HttpStatus.OK,"200","정상적으로 조회되었습니다"),
    _UPDATE_SUCCESS(HttpStatus.OK,"200","정상적으로 수정되었습니다"),
    _DELETE_SUCCESS(HttpStatus.OK,"200","정상적으로 삭제되었습니다"),
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
