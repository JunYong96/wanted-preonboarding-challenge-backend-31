package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Product_images 테이블과 매핑되는 JPA 엔티티 클래스
 * 제품의 이미지 정보를 표현하는 엔티티
 */
@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductVO product;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "alt_text", length = 255)
    private String altText;

    @Column(name = "is_primary")
    @Builder.Default
    private Boolean isPrimary = false;

    @Column(name = "display_order")
    @Builder.Default
    private Integer displayOrder = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private ProductOptionVO option;
}
