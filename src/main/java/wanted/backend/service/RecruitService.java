package wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.common.exception.CustomException;
import wanted.backend.common.exception.ErrorCode;
import wanted.backend.dto.RecruitRequestDTO;
import wanted.backend.entity.CompanyEntity;
import wanted.backend.entity.RecruitEntity;
import wanted.backend.repository.CompanyRepository;
import wanted.backend.repository.RecruitRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    //1. 채용공고를 등록합니다.
    @Transactional
    public void register(RecruitRequestDTO dto) {
        CompanyEntity companyEntity = companyFindById(dto.getCompanyId());

        RecruitEntity recruitEntity = recruitRepository.save(RecruitEntity.builder()
                .company(companyEntity)
                .compensation(dto.getCompensation())
                .position(dto.getPosition())
                .content(dto.getContent())
                .techStack(dto.getTechStack())
                .build());

        Optional<RecruitEntity> currentRecruitEntity = recruitRepository.findById(recruitEntity.getId());

        if(currentRecruitEntity.isEmpty()) {
            throw new CustomException(ErrorCode.DB_SAVE_ERROR);
        }
    }

    private CompanyEntity companyFindById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMPANY_NOT_FOUND));
    }
}
