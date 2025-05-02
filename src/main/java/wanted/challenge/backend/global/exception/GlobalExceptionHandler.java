package wanted.challenge.backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * 전역 예외 처리 핸들러
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * BaseException 및 자식 예외 처리
     */
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        log.error("BaseException: {}", e.getMessage(), e);

        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetails()));
    }

    /**
     * Bean Validation 예외 처리 (@Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation Exception: {}", e.getMessage(), e);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(BaseException.ErrorCode.INVALID_INPUT, "잘못된 입력 데이터", errors));
    }

    /**
     * @ModelAttribute 바인딩 오류 처리
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("Binding Exception: {}", e.getMessage(), e);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(BaseException.ErrorCode.INVALID_INPUT, "잘못된 입력 데이터", errors));
    }

    /**
     * 타입 변환 오류 처리
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Type Mismatch Exception: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        BaseException.ErrorCode.INVALID_INPUT,
                        "잘못된 입력 데이터",
                        Map.of(
                                "parameter", e.getName(),
                                "requiredType", e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "unknown",
                                "providedValue", e.getValue() != null ? e.getValue().toString() : "null"
                        )
                ));
    }

    /**
     * 처리되지 않은 모든 예외를 처리
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unhandled Exception: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(BaseException.ErrorCode.INTERNAL_ERROR, "서버 내부 오류"));
    }
}
