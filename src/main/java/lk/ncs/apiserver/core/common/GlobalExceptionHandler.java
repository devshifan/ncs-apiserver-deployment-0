package lk.ncs.apiserver.core.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleException(Exception e){
        APIResponse apiResponse = new APIResponse();
        return ResponseEntity.status(500).body(apiResponse);
    }
    @ExceptionHandler
    public ResponseEntity handleBadRequestException(BadRequestException e){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setError(e.getErrors());

        return  ResponseEntity.status(400).body(apiResponse);
    }
}
