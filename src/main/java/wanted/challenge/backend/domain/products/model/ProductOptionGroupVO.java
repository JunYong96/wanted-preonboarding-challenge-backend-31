package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Product_option_groups 테이블과 매핑되는 JPA 엔티티 클래스
 * 제품의 옵션 그룹을 표현하는 엔티티 (예: 사이즈, 색상 등)
 */
@Entity
@Table(name = "product_option_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOptionGroupVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductVO product;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "display_order")
    @Builder.Default
    private Integer displayOrder = 0;
}