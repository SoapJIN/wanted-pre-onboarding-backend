package wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wanted.backend.entity.RecruitEntity;

import java.util.List;


@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity, Long> {
    @Query("select R from RecruitEntity R where R.company.name like %:keyword% "
            +"or R.position like %:keyword% "
            +"or R.techStack like %:keyword%")
    List<RecruitEntity> findAllByKeyword(@Param("keyword") String keyword);
}
