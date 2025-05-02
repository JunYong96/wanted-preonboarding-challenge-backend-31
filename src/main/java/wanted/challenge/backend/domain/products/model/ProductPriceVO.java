package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Product_prices 테이블과 매핑되는 JPA 엔티티 클래스
 */
@Entity
@Table(name = "product_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPriceVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductVO product;

    @Column(name = "base_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "sale_price", precision = 12, scale = 2)
    private BigDecimal salePrice;

    @Column(name = "cost_price", precision = 12, scale = 2)
    private BigDecimal costPrice;

    @Column(name = "currency", length = 3)
    @Builder.Default
    private String currency = "KRW";

    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate;
}