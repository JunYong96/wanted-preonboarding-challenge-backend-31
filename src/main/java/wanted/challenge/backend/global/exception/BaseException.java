package wanted.challenge.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * API 에러의 기본 예외 클래스
 */
@Getter
public class BaseException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
    private final Object details;

    public BaseException(String code, String message, HttpStatus status, Object details) {
        super(message);
        this.code = code;
        this.status = status;
        this.details = details;
    }

    public BaseException(String code, String message, HttpStatus status) {
        this(code, message, status, null);
    }

    /**
     * 에러 코드 정의
     */
    public static class ErrorCode {
        public static final String INVALID_INPUT = "INVALID_INPUT";
        public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
        public static final String UNAUTHORIZED = "UNAUTHORIZED";
        public static final String FORBIDDEN = "FORBIDDEN";
        public static final String CONFLICT = "CONFLICT";
        public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
    }
}

