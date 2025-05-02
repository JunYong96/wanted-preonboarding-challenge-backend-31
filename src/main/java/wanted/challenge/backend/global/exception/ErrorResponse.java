package wanted.challenge.backend.global.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

/**
 * API 에러 응답 DTO
 */
@Getter
@Builder
public class ErrorResponse {
    private final boolean success;
    private final Error error;

    @Getter
    @Builder
    public static class Error {
        private final String code;
        private final String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final Object details;
    }

    /**
     * 에러 응답 생성
     */
    public static ErrorResponse of(String code, String message, Object details) {
        return ErrorResponse.builder()
                .success(false)
                .error(Error.builder()
                        .code(code)
                        .message(message)
                        .details(details)
                        .build())
                .build();
    }

    /**
     * 에러 응답 생성 (상세 정보 없음)
     */
    public static ErrorResponse of(String code, String message) {
        return of(code, message, null);
    }
}