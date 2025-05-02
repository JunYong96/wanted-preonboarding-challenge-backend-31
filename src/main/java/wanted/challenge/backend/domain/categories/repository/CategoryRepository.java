package wanted.challenge.backend.domain.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.categories.model.CategoryVO;

public interface CategoryRepository extends JpaRepository<CategoryVO, Long> {
}
