package wanted.challenge.backend.domain.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import wanted.challenge.backend.domain.products.model.ProductVO;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResDto {

    @Getter
    @Setter
    public static class ProductInsertResDto {
        private Long id;
        private String name;
        private String slug;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
        private ZonedDateTime created_at;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
        private ZonedDateTime updated_at;

        public ProductInsertResDto(ProductVO productVO) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            ZonedDateTime createdAt = productVO.getCreatedAt() != null ? productVO.getCreatedAt().toInstant().atZone(ZoneOffset.UTC) : null;
            ZonedDateTime updatedAt = productVO.getUpdatedAt() != null ? productVO.getUpdatedAt().toInstant().atZone(ZoneOffset.UTC) : null;
            this.setId(productVO.getId());
            this.setName(productVO.getName());
            this.setSlug(productVO.getSlug());
            this.setCreated_at(createdAt);
            this.setUpdated_at(updatedAt);
        }
    }
}
