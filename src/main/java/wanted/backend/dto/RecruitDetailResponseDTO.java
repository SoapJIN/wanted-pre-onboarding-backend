package wanted.backend.dto;

import lombok.Builder;
import lombok.Getter;
import wanted.backend.entity.RecruitEntity;

import java.util.List;

@Getter
@Builder
public class RecruitDetailResponseDTO {
    private Long id;
    private String companyName;
    private String companyCountry;
    private String companyArea;
    private String position;
    private int compensation;
    private String content;
    private String techStack;
    private List<Long> companyRecruitIdList;

    public static RecruitDetailResponseDTO fromEntity(RecruitEntity entity, List<Long> companyRecruitIdList) {
        return builder()
                .id(entity.getId())
                .companyName(entity.getCompany().getName())
                .companyCountry(entity.getCompany().getCountry())
                .companyArea(entity.getCompany().getArea())
                .position(entity.getPosition())
                .compensation(entity.getCompensation())
                .content(entity.getContent())
                .techStack(entity.getTechStack())
                .companyRecruitIdList(companyRecruitIdList)
                .build();
    }
}
