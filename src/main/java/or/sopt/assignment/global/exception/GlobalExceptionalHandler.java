package or.sopt.assignment.global.exception;

import or.sopt.assignment.global.api.reponse.ApiResponse;
import or.sopt.assignment.global.api.status.CommonErrorStatus;
import or.sopt.assignment.global.api.status.ErrorDTO;
import or.sopt.assignment.global.api.status.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    /**
     * 구현하다 궁금증이 들었던 부분은 어떻게 이러한 예외가 터질 줄 알고 핸들링을 하는가 였습니다
     *
     * 두번째 메서드처럼 MissingRequestHeaderException 이 터질 수 있겠구나 라고 제가 예상할 뿐, 또 다른 무슨 예외가 터질 줄 모르는데
     * 어떻게 예상하고 핸들링하는 코드를 작성해야할지 의문이 생겼습니다 (아직 해결 못함)
     * */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDTO(
                        e.getErrorStatus().getHttpStatus(),
                        e.getErrorStatus().getCode(),
                        e.getErrorStatus().getMessage())
        );
    }

    // 필수로 받아야하는 파라미터가 들어오지 않으면 발생하는 MissingRequestHeaderException를 핸들링 하였습니다.
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return ApiResponse.fail(CommonErrorStatus._HEADER_NOT_FOUND);
    }
}
