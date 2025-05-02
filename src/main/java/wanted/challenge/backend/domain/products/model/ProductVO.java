package wanted.challenge.backend.domain.products.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import wanted.challenge.backend.domain.products.dto.ReqDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Products 테이블과 매핑되는 JPA 엔티티 클래스
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "slug", nullable = false, length = 255, unique = true)
    private String slug;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private SellerVO seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandVO brand;

    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private String status = "ACTIVE";
}
