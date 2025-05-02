package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductPriceVO;

public interface ProductPriceRepository extends JpaRepository<ProductPriceVO, Long> {
}
