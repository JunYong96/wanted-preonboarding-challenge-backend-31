package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductOptionGroupVO;

public interface ProductOptionGroupRepository extends JpaRepository<ProductOptionGroupVO, Long> {
}
