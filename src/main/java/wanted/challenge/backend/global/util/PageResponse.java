package wanted.challenge.backend.global.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private PaginationInfo pagination;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaginationInfo {
        private long totalItems;
        private int totalPages;
        private int currentPage;
        private int perPage;
    }

    // 페이지네이션 응답 생성 헬퍼 메서드
    public static <T> PageResponse<T> of(List<T> items, long totalItems, int currentPage, int perPage) {
        int totalPages = (int) Math.ceil((double) totalItems / perPage);

        PaginationInfo paginationInfo = PaginationInfo.builder()
                .totalItems(totalItems)
                .totalPages(totalPages)
                .currentPage(currentPage)
                .perPage(perPage)
                .build();

        return PageResponse.<T>builder()
                .items(items)
                .pagination(paginationInfo)
                .build();
    }
}
