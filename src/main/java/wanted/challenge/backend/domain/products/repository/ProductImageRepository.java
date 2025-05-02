package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductImageVO;

public interface ProductImageRepository extends JpaRepository<ProductImageVO, Long> {
}
