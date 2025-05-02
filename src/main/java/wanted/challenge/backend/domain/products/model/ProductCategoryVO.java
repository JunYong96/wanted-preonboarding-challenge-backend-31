package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;
import wanted.challenge.backend.domain.categories.model.CategoryVO;

/**
 * Product_categories 테이블과 매핑되는 JPA 엔티티 클래스
 * 제품과 카테고리 간의 다대다 관계를 표현하는 연결 테이블 엔티티
 */
@Entity
@Table(name = "product_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductVO product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryVO category;

    @Column(name = "is_primary")
    @Builder.Default
    private Boolean isPrimary = false;
}