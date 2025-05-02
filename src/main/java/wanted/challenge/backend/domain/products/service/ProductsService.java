package wanted.challenge.backend.domain.products.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.challenge.backend.domain.products.dto.ReqDto;
import wanted.challenge.backend.domain.products.dto.ResDto;
import wanted.challenge.backend.domain.products.model.*;
import wanted.challenge.backend.domain.products.repository.*;
import wanted.challenge.backend.global.exception.BaseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final SellerRepository sellerRepository;

    private final ProductPriceRepository productPriceRepository;

    private final ProductOptionGroupRepository productOptionGroupRepository;

    private final ProductOptionRepository productOptionRepository;

    private final ProductDetailRepository productDetailRepository;

    private final ProductImageRepository productImageRepository;

    public ResDto.ProductInsertResDto productSave(ReqDto.ProductInsertReqDto reqDto) {
        Optional<ProductVO> optionalProductVO = productRepository.findOneBySlug(reqDto.getSlug());

        if (optionalProductVO.isPresent()) {
            Map<String, Object> details = new HashMap<>();
            details.put("slug", "상품의 slug는 중복될 수 없습니다.");
            throw new BaseException(BaseException.ErrorCode.CONFLICT, "상품 등록에 실패하였습니다.", HttpStatus.CONFLICT, details);
        }

        if (reqDto.getBrand_id() == null || reqDto.getBrand_id() <= 0) {
            Map<String, Object> details = new HashMap<>();
            details.put("brand_id", "상품의 브랜드를 확인할 수 없습니다.");
            throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
        }
        Optional<BrandVO> brandVO = brandRepository.findById(reqDto.getBrand_id());
        if (brandVO.isEmpty()) {
            Map<String, Object> details = new HashMap<>();
            details.put("brand_id", "상품의 브랜드를 확인할 수 없습니다.");
            throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
        }

        if (reqDto.getSeller_id() == null || reqDto.getSeller_id() <= 0) {
            Map<String, Object> details = new HashMap<>();
            details.put("seller_id", "상품의 판매자를 확인할 수 없습니다.");
            throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
        }
        Optional<SellerVO> sellerVO = sellerRepository.findById(reqDto.getSeller_id());
        if (sellerVO.isEmpty()) {
            Map<String, Object> details = new HashMap<>();
            details.put("seller_id", "상품의 판매자를 확인할 수 없습니다.");
            throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
        }

        if (StringUtils.isBlank(reqDto.getName())) {
            Map<String, Object> details = new HashMap<>();
            details.put("name", "상품명은 필수 항목입니다.");
            throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
        }
        ProductVO productVO = ProductVO.builder()
                                       .name(reqDto.getName())
                                       .slug(reqDto.getSlug())
                                       .brand(brandVO.get())
                                       .seller(sellerVO.get())
                                       .shortDescription(reqDto.getShort_description())
                                       .fullDescription(reqDto.getFull_description())
                                       .build();
        productVO = productRepository.save(productVO);

        ObjectMapper objectMapper = new ObjectMapper();
        Map additionalInfos = objectMapper.convertValue(reqDto.getDetail()
                                                              .getAdditional_info(), Map.class);
        Map dimensions = objectMapper.convertValue(reqDto.getDetail()
                                                         .getDimensions(), Map.class);
        ProductDetailVO productDetailVO = ProductDetailVO.builder()
                                                         .additionalInfo(additionalInfos)
                                                         .warrantyInfo(reqDto.getDetail()
                                                                             .getWarranty_info())
                                                         .countryOfOrigin(reqDto.getDetail()
                                                                                .getCountry_of_origin())
                                                         .careInstructions(reqDto.getDetail()
                                                                                 .getCare_instructions())
                                                         .dimensions(dimensions)
                                                         .weight(reqDto.getDetail()
                                                                       .getWeight())
                                                         .product(productVO)
                                                         .build();
        productDetailRepository.save(productDetailVO);

        if (reqDto.getPrice()
                  .getBase_price() == null) {
            Map<String, Object> details = new HashMap<>();
            details.put("base_price", "상품 기준가격은 필수 항목입니다.");
            throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
        }
        ProductPriceVO productPriceVO = ProductPriceVO.builder()
                                                      .basePrice(reqDto.getPrice()
                                                                       .getBase_price())
                                                      .costPrice(reqDto.getPrice()
                                                                       .getCost_price())
                                                      .salePrice(reqDto.getPrice()
                                                                       .getSale_price())
                                                      .currency(reqDto.getPrice()
                                                                      .getCurrency())
                                                      .taxRate(reqDto.getPrice()
                                                                     .getTax_rate())
                                                      .product(productVO)
                                                      .build();
        productPriceRepository.save(productPriceVO);

        if (!reqDto.getOption_groups()
                   .isEmpty()) {
            ProductVO finalProductVO = productVO;
            reqDto.getOption_groups()
                  .forEach(e -> {
                      if (StringUtils.isBlank(e.getName())) {
                          Map<String, Object> details = new HashMap<>();
                          details.put("option_group.name", "상품 옵션 그룹명은 필수 항목입니다.");
                          throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
                      }
                      ProductOptionGroupVO productOptionGroupVO = ProductOptionGroupVO.builder()
                                                                                      .name(e.getName())
                                                                                      .displayOrder(e.getDisplay_order())
                                                                                      .product(finalProductVO)
                                                                                      .build();
                      productOptionGroupVO = productOptionGroupRepository.save(productOptionGroupVO);

                      if (!e.getOptions()
                            .isEmpty()) {
                          ProductOptionGroupVO finalProductOptionGroupVO = productOptionGroupVO;
                          e.getOptions()
                           .forEach(o -> {
                               if (StringUtils.isBlank(o.getName())) {
                                   Map<String, Object> details = new HashMap<>();
                                   details.put("option.name", "상품 옵션명은 필수 항목입니다.");
                                   throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
                               }
                               ProductOptionVO productOptionVO = ProductOptionVO.builder()
                                                                                .optionGroup(finalProductOptionGroupVO)
                                                                                .sku(o.getSku())
                                                                                .stock(o.getStock())
                                                                                .additionalPrice(o.getAdditional_price())
                                                                                .displayOrder(o.getDisplay_order())
                                                                                .name(o.getName())
                                                                                .build();
                               productOptionRepository.save(productOptionVO);
                           });
                      }
                  });
        }

        if (!reqDto.getImages()
                   .isEmpty()) {
            reqDto.getImages()
                  .forEach(e -> {
                      if (StringUtils.isBlank(e.getUrl())) {
                          Map<String, Object> details = new HashMap<>();
                          details.put("image.url", "상품 이미지 url은 필수 항목입니다.");
                          throw new BaseException(BaseException.ErrorCode.INVALID_INPUT, "상품 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST, details);
                      }
                      ProductImageVO productImageVO = ProductImageVO.builder()
                                                                    .url(e.getUrl())
                                                                    .altText(e.getAlt_text())
                                                                    .isPrimary(e.getIs_primary())
                                                                    .displayOrder(e.getDisplay_older())
                                                                    .build();

                      if (e.getOption_id() != null) {
                          Optional<ProductOptionVO> optionalProductOptionVO = productOptionRepository.findById(e.getOption_id());

                          optionalProductOptionVO.ifPresent(productImageVO::setOption);
                      }
                      productImageRepository.save(productImageVO);
                  });
        }

        return new ResDto.ProductInsertResDto(productVO);

    }
}
