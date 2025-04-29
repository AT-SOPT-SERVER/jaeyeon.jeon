package or.sopt.assignment.global.exception;

import or.sopt.assignment.global.reponse.ApiResponse;
import or.sopt.assignment.global.status.ErrorDTO;
import or.sopt.assignment.global.status.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDTO(
                        e.getErrorStatus().getHttpStatus(),
                        e.getErrorStatus().getCode(),
                        e.getErrorStatus().getMessage())
        );
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return ApiResponse.fail(ErrorStatus._HEADER_NOT_FOUND);
    }
}
