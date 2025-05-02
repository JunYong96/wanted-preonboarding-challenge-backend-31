package wanted.challenge.backend.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.challenge.backend.domain.products.dto.ReqDto;
import wanted.challenge.backend.domain.products.service.ProductsService;
import wanted.challenge.backend.global.util.ApiResponse;

import java.util.Map;

@Tag(name = "Products", description = "상품 정보 관리")
@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @PostMapping(path = "/products")
    @SuppressWarnings("unused")
    @Operation(summary = "상품 정보 등록", description = "상품 정보 등록")
    public ResponseEntity<Object> save(
            @Parameter(name = "reqDto", description = "상품 객체", required = true) @RequestBody @Valid
            ReqDto.ProductInsertReqDto reqDto,
            @RequestHeader Map<String, String> headers)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(productsService.productSave(reqDto), "상품이 성공적으로 등록되었습니다."));
    }
}
