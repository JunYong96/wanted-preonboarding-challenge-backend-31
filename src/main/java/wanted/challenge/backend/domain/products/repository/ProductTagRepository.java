package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductTagVO;

public interface ProductTagRepository extends JpaRepository<ProductTagVO, Long> {
}
