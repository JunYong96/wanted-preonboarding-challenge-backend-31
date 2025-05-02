package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Product_details 테이블과 매핑되는 JPA 엔티티 클래스
 */
@Entity
@Table(name = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductVO product;

    @Column(name = "weight", precision = 10, scale = 2)
    private BigDecimal weight;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dimensions", columnDefinition = "jsonb")
    private Map<String, Object> dimensions;

    @Column(name = "materials")
    private String materials;

    @Column(name = "country_of_origin", length = 100)
    private String countryOfOrigin;

    @Column(name = "warranty_info")
    private String warrantyInfo;

    @Column(name = "care_instructions")
    private String careInstructions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "additional_info", columnDefinition = "jsonb")
    private Map<String, Object> additionalInfo;
}