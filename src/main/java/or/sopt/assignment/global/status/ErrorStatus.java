package or.sopt.assignment.global.status;

import org.springframework.http.HttpStatus;

public enum ErrorStatus {

    _POST_TITLE_NOT_NULL(HttpStatus.BAD_REQUEST,"POST4001","게시글의 제목은 필수 입력입니다"),
    _POST_CONTENT_NOT_NULL(HttpStatus.BAD_REQUEST,"POST4002","게시글의 내용은 필수 입력입니다"),
    _POST_TITLE_LENGTH(HttpStatus.BAD_REQUEST,"POST4003","게시글의 길이는 30자를 넘어서는 안됩니다"),
    _POST_TITLE_DUPLICATE(HttpStatus.BAD_REQUEST,"POST4004","게시글의 제목이 중복되었습니다"),
    _POST_CREATE_TIMER(HttpStatus.BAD_REQUEST,"POST4005","게시글은 이전 게시글 작성 후 3분 뒤에 작성 할 수 있습니다");

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
