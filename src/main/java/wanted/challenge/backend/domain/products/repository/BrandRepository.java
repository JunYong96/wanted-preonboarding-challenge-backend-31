package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.BrandVO;

public interface BrandRepository extends JpaRepository<BrandVO, Long> {
}
