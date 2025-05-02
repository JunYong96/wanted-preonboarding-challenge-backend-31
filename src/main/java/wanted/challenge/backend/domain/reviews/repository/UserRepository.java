package wanted.challenge.backend.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.reviews.model.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Long> {
}
