package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductOptionVO;

public interface ProductOptionRepository extends JpaRepository<ProductOptionVO, Long> {
}
