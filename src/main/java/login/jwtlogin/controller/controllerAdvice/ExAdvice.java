package login.jwtlogin.controller.controllerAdvice;


import login.jwtlogin.result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExAdvice {

    /**
     * 1) bean validation 오류
     * 2) type error
     */

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResult> error(MethodArgumentNotValidException e) {
        List<ErrorResult> errorList = new ArrayList<>();

        String code = "";
        String message = "";

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            code = error.getCode();
            message = error.getDefaultMessage();
            errorList.add(new ErrorResult(code, message));
        }
        return errorList;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult error(HttpMessageNotReadableException e) {

        return new ErrorResult("JOIN_TYPE_ERROR", "타입이 맞지 않습니다");
    }

    //------------------------------------------------------------------
    // 조회 실패 시 - Illegal exception -> 400 으로 변환해서 예외처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult error(IllegalArgumentException e) {
        return new ErrorResult("BAD_REQUEST", e.getMessage());
    }
}
