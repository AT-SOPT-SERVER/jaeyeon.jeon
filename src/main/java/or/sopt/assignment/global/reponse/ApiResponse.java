package or.sopt.assignment.global.reponse;

import or.sopt.assignment.global.status.ErrorStatus;
import or.sopt.assignment.global.status.SuccessStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    /**
     * 왜 응답 DTO를 ResponseEntity로 감싸서 보내야하는지 고민을 해보았습니다.
     *
     * 응답자체에는 큰 문제가 없을 수 있지만, 헤더를 컨트롤해야하는 경우를 포함하여 HTTP Response를 직접적으로 컨트롤해야하는 작업때,
     * HttpServeletResponse 객체를 직접 이용하는게 아닌 ResposneEntity를 이용하여 조작하는게 보다 통일성있고 안정적인 방법이라는 생각이 들었습니다.
     * HttpServeletResponse 객체에서 헤더를 파싱하고 넣는 작업은 아무래도 파라미터로 계속 응답 서블릿 객체를 서비스로, 또 다른 헬퍼 클래스등으로 움직여야하는데,
     *  RespponseEntity는 그럴필요가 없기 때문이죠
     *
     * 하지만 그렇게 감싸서 보내려다보니 컨트롤러의 응답로직이 다소 통일적이지 못하다는 느낌을 받았습니다.
     * 기존의 코드는
     * ResponseEntity.ok 의 body안에 ApiResponse.onSuccess(SuccessStatus._CREATED_SUCCESS) 등의 값을 넣어서 보내는 형식이었습니다.
     * 즉 응답을 보낼때마다 REsponseEntity의 필드들을 직접 구현해서 응답해야한다는 점이었습니다.
     * 이 부분이 다소 의미없는 반복작업이라고 생각하였습니다.
     *
     * 그래서 응답 Body와 성공 상태코드를 파라미터로 받아서 한 번에 ResponseEntity로 응답하여 통일성 있는 로직을 구현할 수 있도록 하였습니다
     * */
    public static <T> ResponseEntity<ResponseDTO<?>> ok(SuccessStatus successStatus, T result) {
        return ResponseEntity
                .status(200)
                .body(
                        new ResponseDTO<>(
                                successStatus.getHttpStatus(),
                                successStatus.getCode(),
                                successStatus.getMessage(),
                                true,
                                result
                        )
                );
    }

    public static <T> ResponseEntity<ResponseDTO<?>> ok(SuccessStatus successStatus) {
        return ResponseEntity
                .status(200)
                .body(
                        new ResponseDTO<>(
                                successStatus.getHttpStatus(),
                                successStatus.getCode(),
                                successStatus.getMessage(),
                                true
                        )
                );
    }


    public static <T> ResponseEntity<ResponseDTO<?>> okWithHeader(SuccessStatus successStatus, HttpHeaders headers) {
        return ResponseEntity
                .status(200)
                .headers(headers)
                .body(
                        new ResponseDTO<>(
                                successStatus.getHttpStatus(),
                                successStatus.getCode(),
                                successStatus.getMessage(),
                                true
                        )
                );
    }

    public static <T> ResponseEntity<ResponseDTO<?>> okWithHeader(SuccessStatus successStatus, HttpHeaders headers,T result) {
        return ResponseEntity
                .status(200)
                .headers(headers)
                .body(
                        new ResponseDTO<>(
                                successStatus.getHttpStatus(),
                                successStatus.getCode(),
                                successStatus.getMessage(),
                                true,
                                result
                        )
                );
    }


    // 근데 예외사항은 보통 서비스 단에서 잡히기 때문에 이렇게 컨트롤러에서 실패 응답을 보낼 상황이 잘 없지 않을까 하는 생각이...
    public static <T> ResponseEntity<ResponseDTO<?>> fail(ErrorStatus errorStatus) {
        return ResponseEntity
                .status(400)
                .body(
                        new ResponseDTO<>(
                                errorStatus.getHttpStatus(),
                                errorStatus.getCode(),
                                errorStatus.getMessage(),
                                false
                        )
                );
    }


}
