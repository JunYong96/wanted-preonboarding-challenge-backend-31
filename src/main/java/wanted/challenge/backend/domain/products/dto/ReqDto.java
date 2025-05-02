package wanted.challenge.backend.domain.products.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class ReqDto {

    @Getter
    @Setter
    public static class ProductInsertReqDto {
        private String name;
        private String slug;
        private String short_description;
        private String full_description;
        private Long seller_id;
        private Long brand_id;
        private String status;
        private ProductDetailReqDto detail;
        private ProductPriceReqDto price;
        private List<ProductCategoryReqDto> categories;
        private List<ProductOptionGroupReqDto> option_groups;
        private List<ProductImageReqDto> images;
        private List<Integer> tags;
    }

    @Getter
    @Setter
    public static class ProductDetailReqDto {
        private BigDecimal weight;
        private ProductDimensionsReqDto dimensions;
        private String materials;
        private String country_of_origin;
        private String warranty_info;
        private String care_instructions;
        private ProductAdditionalInfoReqDto additional_info;
    }

    @Getter
    @Setter
    public static class ProductDimensionsReqDto {
        private Long width;
        private Long height;
        private Long depth;
    }

    @Getter
    @Setter
    public static class ProductAdditionalInfoReqDto {
        private Boolean assembly_required;
        private String assembly_time;
    }

    @Getter
    @Setter
    public static class ProductPriceReqDto {
        private BigDecimal base_price;
        private BigDecimal sale_price;
        private BigDecimal cost_price;
        private String currency;
        private BigDecimal tax_rate;
    }

    @Getter
    @Setter
    public static class ProductCategoryReqDto {
        private Long category_id;
        private Boolean is_primary;
    }

    @Getter
    @Setter
    public static class ProductOptionGroupReqDto {
        private String name;
        private Integer display_order;
        private List<ProductOptionReqDto> options;
    }

    @Getter
    @Setter
    public static class ProductOptionReqDto {
        private String name;
        private BigDecimal additional_price;
        private String sku;
        private Integer stock;
        private Integer display_order;
    }

    @Getter
    @Setter
    public static class ProductImageReqDto {
        private String url;
        private String alt_text;
        private Boolean is_primary;
        private Integer display_older;
        private Long option_id;
    }
}
