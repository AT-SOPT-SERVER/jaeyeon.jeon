package or.sopt.assignment.global;

import or.sopt.assignment.global.errorStatus.ErrorDTO;
import or.sopt.assignment.global.handler.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDTO(HttpStatus.BAD_REQUEST,"BAD400", e.getMessage())
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDTO(
                        e.getErrorStatus().getHttpStatus(),
                        e.getErrorStatus().getCode(),
                        e.getErrorStatus().getMessage())
        );
    }
}
