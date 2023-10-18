package wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.backend.entity.RecruitEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity, Long> {
}
