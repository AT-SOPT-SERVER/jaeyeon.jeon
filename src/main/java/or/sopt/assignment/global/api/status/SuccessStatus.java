package or.sopt.assignment.global.api.status;

import org.springframework.http.HttpStatus;

public enum SuccessStatus {

    /**
     * 성공 시 반환하는 status는 종류가 많지 않고, 이를 분리하는게 좋은지 사실 고민이 되었습니다. 이걸 구현하는 순간 controller 응답에
     * 따로 넣어줘야하는 코드가 늘어나는 것 같아서
     *
     * 이게 없이 그냥 200/정상처리되었습니다 를 고정적으로 보내는게 더 좋지 않을지...고민이 되네요.
     * */
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
