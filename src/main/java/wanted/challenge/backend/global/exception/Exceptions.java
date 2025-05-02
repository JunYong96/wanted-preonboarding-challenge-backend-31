package wanted.challenge.backend.global.exception;

import org.springframework.http.HttpStatus;

public class Exceptions {
    /**
     * 400 Bad Request 에러를 나타내는 예외
     */
    public static class BadRequestException extends BaseException {
        public BadRequestException(String message, Object details) {
            super(ErrorCode.INVALID_INPUT, message != null ? message : "잘못된 입력 데이터", HttpStatus.BAD_REQUEST, details);
        }

        public BadRequestException(String message) {
            super(ErrorCode.INVALID_INPUT, message != null ? message : "잘못된 입력 데이터", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 401 Unauthorized 에러를 나타내는 예외
     */
    public static class UnauthorizedException extends BaseException {
        public UnauthorizedException(String message, Object details) {
            super(ErrorCode.UNAUTHORIZED, message != null ? message : "인증되지 않은 요청", HttpStatus.UNAUTHORIZED, details);
        }

        public UnauthorizedException(String message) {
            super(ErrorCode.UNAUTHORIZED, message != null ? message : "인증되지 않은 요청", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 403 Forbidden 에러를 나타내는 예외
     */
    public static class ForbiddenException extends BaseException {
        public ForbiddenException(String message, Object details) {
            super(ErrorCode.FORBIDDEN, message != null ? message : "권한이 없는 요청", HttpStatus.FORBIDDEN, details);
        }

        public ForbiddenException(String message) {
            super(ErrorCode.FORBIDDEN, message != null ? message : "권한이 없는 요청", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 404 Not Found 에러를 나타내는 예외
     */
    public static class NotFoundException extends BaseException {
        public NotFoundException(String message, Object details) {
            super(ErrorCode.RESOURCE_NOT_FOUND, message != null ? message : "요청한 리소스를 찾을 수 없음", HttpStatus.NOT_FOUND, details);
        }

        public NotFoundException(String message) {
            super(ErrorCode.RESOURCE_NOT_FOUND, message != null ? message : "요청한 리소스를 찾을 수 없음", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 409 Conflict 에러를 나타내는 예외
     */
    public static class ConflictException extends BaseException {
        public ConflictException(String message, Object details) {
            super(ErrorCode.CONFLICT, message != null ? message : "리소스 충돌 발생", HttpStatus.CONFLICT, details);
        }

        public ConflictException(String message) {
            super(ErrorCode.CONFLICT, message != null ? message : "리소스 충돌 발생", HttpStatus.CONFLICT);
        }
    }

    /**
     * 500 Internal Server Error 에러를 나타내는 예외
     */
    public static class InternalServerException extends BaseException {
        public InternalServerException(String message, Object details) {
            super(ErrorCode.INTERNAL_ERROR, message != null ? message : "서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR, details);
        }

        public InternalServerException(String message) {
            super(ErrorCode.INTERNAL_ERROR, message != null ? message : "서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
