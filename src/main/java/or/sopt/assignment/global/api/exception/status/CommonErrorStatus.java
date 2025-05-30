package or.sopt.assignment.global.api.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CommonErrorStatus implements ErrorStatus {

    /**
     * 제가 생각한 에러코드를 작성하는 좋은 방법은
     * 예외의 주체 도메인 + HTTP CODE(200,300,400,500) + index 라고 생각했습니다!
     *
     * 더 좋은 방법이 있으면 말씀해주세요
     *
     * 그리고 추가로 지금 ErrorStatus를 작성할때도 이름을 (도메인_예외 내용요약) 이렇게 구성하고 있는데 이것도 정확한 규칙없이 하는 느낌이라
     * 좋은 방법있으면 말씀해주세요
     *
     * 05/30
     * 인터페이스를 분리하면서 이전의 status가 이리로 오게 되었습니다....
     * 다른 할일이 많아서 이것도 나중에 수정 하겠습니다....!
     * */
    _HEADER_NOT_FOUND(HttpStatus.NOT_FOUND,"HEADER4001","헤더를 찾을 수 없습니다"),

    _USER_NOT_FOUND(HttpStatus.NOT_FOUND,"USER4001","회원을 찾을 수 없습니다"),
    _USER_NAME_LENGTH(HttpStatus.BAD_REQUEST,"USER4002","회원의 이름은 10자를 넘을 수 없습니다"),

    _POST_TITLE_NOT_NULL(HttpStatus.BAD_REQUEST,"POST4001","게시글의 제목은 필수 입력입니다"),
    _POST_CONTENT_NOT_NULL(HttpStatus.BAD_REQUEST,"POST4002","게시글의 내용은 필수 입력입니다"),
    _POST_TITLE_LENGTH(HttpStatus.BAD_REQUEST,"POST4003","게시글의 길이는 30자를 넘을 수 없습니다"),
    _POST_TITLE_DUPLICATE(HttpStatus.BAD_REQUEST,"POST4004","게시글의 제목이 중복되었습니다"),
    _POST_CONTENT_LENGTH(HttpStatus.BAD_REQUEST,"POST4005","게시글의 내용은 1000자를 넘을 수 없습니다"),
    _POST_CREATE_TIMER(HttpStatus.BAD_REQUEST,"POST4006","게시글은 이전 게시글 작성 후 3분 뒤에 작성 할 수 있습니다"),
    _POST_NOT_FOUND(HttpStatus.BAD_REQUEST,"POST4007","게시글을 찾을 수 없습니다"),
    _POST_TAG_NOT_FOUND(HttpStatus.BAD_REQUEST,"POST4008","게시글 태그가 일치하지 않습니다"),;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
