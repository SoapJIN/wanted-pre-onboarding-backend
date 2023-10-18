package wanted.backend.repository;

import wanted.backend.entity.ApplyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends CrudRepository<ApplyEntity, Long> {
    ApplyEntity findByRecruitIdAndUserId(Long recruitId, Long userId);
}
