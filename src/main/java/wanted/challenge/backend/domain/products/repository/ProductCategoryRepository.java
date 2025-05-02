package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductCategoryVO;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryVO, Long> {
}
