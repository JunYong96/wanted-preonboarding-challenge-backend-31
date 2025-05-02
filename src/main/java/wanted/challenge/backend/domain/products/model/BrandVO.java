package wanted.challenge.backend.domain.products.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Brands 테이블과 매핑되는 JPA 엔티티 클래스
 */
@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "slug", nullable = false, length = 100, unique = true)
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "website", length = 255)
    private String website;
}
