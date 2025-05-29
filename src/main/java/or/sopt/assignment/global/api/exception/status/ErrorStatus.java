package or.sopt.assignment.global.api.exception.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public interface ErrorStatus {

    /**
     * ErrorStatus가 하나의 클래스에 너무 몰려있다는 생각을 했음
     * 하지만 이걸 막무가내로 분화하자니 너무 클래스들이 더러워져서
     *
     * 1. 인터페이스로 ErrorStatus를 분리하고
     * 2. 각 예외 검증로직은 ErrorStatus 타입을 파라미터로 받도록 구현
     * 3. 그리고 각 상세 ErrorStatus 는 인터페이스를 구현하도록 한다
     *
     * */
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}