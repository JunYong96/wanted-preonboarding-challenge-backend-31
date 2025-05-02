package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductDetailVO;

public interface ProductDetailRepository extends JpaRepository<ProductDetailVO, Long> {
}
