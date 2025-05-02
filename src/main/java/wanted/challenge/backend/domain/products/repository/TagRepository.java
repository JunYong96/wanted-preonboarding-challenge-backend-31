package wanted.challenge.backend.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.challenge.backend.domain.products.model.TagVO;

public interface TagRepository extends JpaRepository<TagVO, Long> {
}
