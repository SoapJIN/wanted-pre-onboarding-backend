package wanted.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitRequestDTO {
    private Long companyId;
    private String position;
    private int compensation;
    private String content;
    private String techStack;
}
