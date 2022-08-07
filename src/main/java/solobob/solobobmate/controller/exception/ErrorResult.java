package solobob.solobobmate.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class ErrorResult {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int code;
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    public ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
