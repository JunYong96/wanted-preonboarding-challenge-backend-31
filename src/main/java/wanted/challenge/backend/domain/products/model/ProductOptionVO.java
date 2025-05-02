package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Product_options 테이블과 매핑되는 JPA 엔티티 클래스
 * 제품 옵션 그룹에 속한 구체적인 옵션 값을 표현하는 엔티티 (예: S, M, L 또는 빨강, 파랑, 검정 등)
 */
@Entity
@Table(name = "product_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOptionVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    private ProductOptionGroupVO optionGroup;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "additional_price", precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal additionalPrice = BigDecimal.ZERO;

    @Column(name = "sku", length = 100)
    private String sku;

    @Column(name = "stock")
    @Builder.Default
    private Integer stock = 0;

    @Column(name = "display_order")
    @Builder.Default
    private Integer displayOrder = 0;
}