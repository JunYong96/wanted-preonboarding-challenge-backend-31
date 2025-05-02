package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.SellerVO;

public interface SellerRepository extends JpaRepository<SellerVO, Long> {
}
