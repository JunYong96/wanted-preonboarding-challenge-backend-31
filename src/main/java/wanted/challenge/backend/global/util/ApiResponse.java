package wanted.challenge.backend.global.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    // 성공 응답 생성 (메시지 포함)
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .build();
    }

    // 성공 응답 생성 (기본 메시지)
    public static <T> ApiResponse<T> success(T data) {
        return success(data, "요청이 성공적으로 처리되었습니다.");
    }

    // 실패 응답 생성
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}
