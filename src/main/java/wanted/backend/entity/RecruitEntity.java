package wanted.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CompanyEntity company;

    @Column
    private String position;

    @Column
    private int compensation;

    @Column
    private String content;

    @Column
    private String techStack;

    @Builder
    public RecruitEntity(CompanyEntity company, String position, int compensation, String content, String techStack) {
        this.company = company;
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
    }

    public void update(String position, int compensation, String content, String techStack) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
    }
}
