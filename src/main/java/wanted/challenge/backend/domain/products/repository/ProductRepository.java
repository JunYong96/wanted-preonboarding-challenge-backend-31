package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.ProductVO;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductVO, Long> {
    Optional<ProductVO> findOneBySlug(String slug);
}
