package sign_up.demo.globaleex;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sign_up.demo.exceptions.DemoBaseException;
import sign_up.demo.model.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Database error: " + extractConstraintViolationMessage(ex);
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message);
    }

    private String extractConstraintViolationMessage(DataIntegrityViolationException ex) {
        return ex.getMostSpecificCause().getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DemoBaseException.class)
    public ResponseEntity<Map<String, String>> handleDemoBaseException(DemoBaseException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("suggestion", "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and be at least 8 characters long.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
