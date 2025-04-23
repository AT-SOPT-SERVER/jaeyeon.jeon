package or.sopt.assignment.apiPayLoad.code.status;


import lombok.AllArgsConstructor;
import lombok.Getter;
import or.sopt.assignment.apiPayLoad.code.BaseErrorCode;
import or.sopt.assignment.apiPayLoad.code.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    _POST_TITLE_EXSIST(HttpStatus.BAD_REQUEST,"POST4001","제목은 필수 입력값입니다"),
    _POST_TITLE_LENGTH(HttpStatus.BAD_REQUEST,"POST4002","제목의 길이는 30자를 넘을 수 없습니다"),
    _POST_TITLE_DUPLICATE(HttpStatus.BAD_REQUEST,"POST4003","제목이 중복되었습니다"),;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}