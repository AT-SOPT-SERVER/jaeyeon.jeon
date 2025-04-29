package or.sopt.assignment.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import or.sopt.assignment.global.errorStatus.SuccessStatus;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"isSuccess","httpStatus", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public ApiResponse(HttpStatus httpStatus, String code, String message, Boolean isSuccess, T result) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public ApiResponse(HttpStatus httpStatus, String code, String message, Boolean isSuccess) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public static  <T> ApiResponse<T> onSuccess(SuccessStatus successStatus, T result){
        return new ApiResponse<>(
                successStatus.getHttpStatus(),
                successStatus.getCode(),
                successStatus.getMessage(),
                true,
                result
        );
    }

    public static  <T> ApiResponse<T> onSuccess(SuccessStatus successStatus){
        return new ApiResponse<>(
                successStatus.getHttpStatus(),
                successStatus.getCode(),
                successStatus.getMessage(),
                true
        );
    }

    public static  <T> ApiResponse<T> onFailure(T result){
        return new ApiResponse<>(
                HttpStatus.BAD_GATEWAY,
                "COMMON500",
                "서버문제가 발생하였습니다. 개발자에게 문의 주세요",
                false
        );
    }

    public String getCode() {
        return code;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
