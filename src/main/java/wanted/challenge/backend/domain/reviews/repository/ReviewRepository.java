package wanted.challenge.backend.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.reviews.model.ReviewVO;

public interface ReviewRepository extends JpaRepository<ReviewVO, Long> {
}
