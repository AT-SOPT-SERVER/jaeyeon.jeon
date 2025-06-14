package or.sopt.assignment.domain.comment.exception;

import lombok.Getter;
import or.sopt.assignment.global.api.exception.status.ErrorStatus;
import org.springframework.http.HttpStatus;

@Getter
public enum CommentErrorStatus implements ErrorStatus {
    COMMENT_TOO_LONG(HttpStatus.BAD_REQUEST,"COMMENT4001","댓글의 길이는 300자를 넘을 수 없습니다"),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST,"COMMENT4002","댓글을 찾을 수 없습니다"),
    COMMENT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMENT4003","댓글 수정은 작성자만 가능합니다")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    CommentErrorStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
