package wanted.backend.dto;

import lombok.Builder;
import lombok.Getter;
import wanted.backend.entity.RecruitEntity;

@Getter
@Builder
public class RecruitResponseDTO {
    private Long id;
    private String companyName;
    private String companyCountry;
    private String companyArea;
    private String position;
    private int compensation;
    private String techStack;

    public static RecruitResponseDTO fromEntity(RecruitEntity entity) {
        return builder()
                .id(entity.getId())
                .companyName(entity.getCompany().getName())
                .companyCountry(entity.getCompany().getCountry())
                .companyArea(entity.getCompany().getArea())
                .position(entity.getPosition())
                .compensation(entity.getCompensation())
                .techStack(entity.getTechStack())
                .build();
    }
}
