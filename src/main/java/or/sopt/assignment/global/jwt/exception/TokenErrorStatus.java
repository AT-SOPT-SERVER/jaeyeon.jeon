package or.sopt.assignment.global.jwt.exception;

import lombok.Getter;
import or.sopt.assignment.global.api.status.ErrorStatus;
import org.springframework.http.HttpStatus;

@Getter
public enum TokenErrorStatus implements ErrorStatus {

    TOKEN_ACCESS_EXPIRED(HttpStatus.UNAUTHORIZED,"TOKEN4001","토큰이 만료되었습니다"),
    TOKEN_INVALID_TYPE(HttpStatus.UNAUTHORIZED,"TOKEN4002","액세스 토큰을 찾을 수 없습니다" );


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    TokenErrorStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
