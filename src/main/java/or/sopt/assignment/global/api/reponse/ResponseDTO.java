package or.sopt.assignment.global.api.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"isSuccess","httpStatus", "code", "message", "result"})
public class ResponseDTO<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public ResponseDTO(HttpStatus httpStatus, String code, String message, Boolean isSuccess, T result) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public ResponseDTO(HttpStatus httpStatus, String code, String message, Boolean isSuccess) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
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
