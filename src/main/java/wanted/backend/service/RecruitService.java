package wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.common.exception.CustomException;
import wanted.backend.common.exception.ErrorCode;
import wanted.backend.dto.RecruitDetailResponseDTO;
import wanted.backend.dto.RecruitRequestDTO;
import wanted.backend.dto.RecruitResponseDTO;
import wanted.backend.entity.CompanyEntity;
import wanted.backend.entity.RecruitEntity;
import wanted.backend.repository.CompanyRepository;
import wanted.backend.repository.RecruitRepository;

import java.util.List;
import java.util.Objects;
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

    //2. 채용공고를 수정합니다.
    @Transactional
    public void update(RecruitRequestDTO dto) {
        RecruitEntity recruitEntity = recruitRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));

        recruitEntity.update(dto.getPosition(), dto.getCompensation(), dto.getContent(), dto.getTechStack());
    }

    //3. 채용공고를 삭제합니다.
    @Transactional
    public void delete(Long id) {
        RecruitEntity recruitEntity = recruitRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));

        recruitRepository.delete(recruitEntity);
    }

    //4-1. 채용공고 목록을 가져옵니다.
    @Transactional(readOnly = true)
    public List<RecruitResponseDTO> getList() {
        return recruitRepository.findAll().stream()
                .map(RecruitResponseDTO::fromEntity).toList();
    }

    //4-2. 채용공고 검색 기능 구현
    @Transactional(readOnly = true)
    public List<RecruitResponseDTO> getListByKeyword(String keyword) {
        return recruitRepository.findAllByKeyword(keyword).stream()
                .map(RecruitResponseDTO::fromEntity).toList();
    }

    //5. 채용 상세 페이지를 가져옵니다.
    @Transactional(readOnly = true)
    public RecruitDetailResponseDTO getDetail(Long id) {
        RecruitEntity recruitEntity = recruitRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));

        CompanyEntity companyEntity = companyFindById(recruitEntity.getCompany().getId());

        List<Long> companyRecruitIdList = recruitRepository.findAllByCompanyId(companyEntity.getId()).stream()
                .map(RecruitEntity::getId)
                .filter(e -> !Objects.equals(e, recruitEntity.getId()))
                .toList();

        return RecruitDetailResponseDTO.fromEntity(recruitEntity,companyRecruitIdList);
    }
}
