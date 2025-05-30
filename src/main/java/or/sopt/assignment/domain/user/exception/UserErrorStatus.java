package or.sopt.assignment.domain.user.exception;

import lombok.Getter;
import or.sopt.assignment.global.api.exception.status.ErrorStatus;
import org.springframework.http.HttpStatus;

@Getter
public enum UserErrorStatus implements ErrorStatus {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"USER4001","회원을 찾을 수 없습니다")
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    UserErrorStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
