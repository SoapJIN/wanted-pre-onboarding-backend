package wanted.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "apply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RecruitEntity recruit;

    @ManyToOne
    private UserEntity user;

    @Builder
    public ApplyEntity(RecruitEntity recruitEntity, UserEntity userEntity) {
        this.recruit = recruitEntity;
        this.user = userEntity;
    }
}
