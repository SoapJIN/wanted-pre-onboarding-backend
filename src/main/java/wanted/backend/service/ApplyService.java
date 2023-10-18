package wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.common.exception.CustomException;
import wanted.backend.common.exception.ErrorCode;
import wanted.backend.dto.ApplyRequestDTO;
import wanted.backend.entity.ApplyEntity;
import wanted.backend.entity.RecruitEntity;
import wanted.backend.entity.UserEntity;
import wanted.backend.repository.ApplyRepository;
import wanted.backend.repository.RecruitRepository;
import wanted.backend.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;

    //6. 사용자는 채용공고에 지원합니다.
    @Transactional

    public void register(ApplyRequestDTO dto) {
        RecruitEntity recruitEntity = recruitRepository.findById(dto.getRecruitId())
                .orElseThrow(()-> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));
        UserEntity userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(applyRepository.findByRecruitIdAndUserId(recruitEntity.getId(), userEntity.getId()) != null) {
            throw new CustomException(ErrorCode.ALREADY_PROCESSED);
        }

        ApplyEntity applyEntity = applyRepository.save(ApplyEntity.builder()
                .recruitEntity(recruitEntity)
                .userEntity(userEntity)
                .build());

        Optional<ApplyEntity> currentApplyEntity = applyRepository.findById(applyEntity.getId());

        if(currentApplyEntity.isEmpty()) {
            throw new CustomException(ErrorCode.DB_SAVE_ERROR);
        }
    }
}
