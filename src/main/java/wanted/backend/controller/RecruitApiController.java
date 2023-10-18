package wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.backend.dto.RecruitRequestDTO;
import wanted.backend.service.RecruitService;

@RestController
@RequestMapping("/api/recruits")
@RequiredArgsConstructor
public class RecruitApiController {
    private final RecruitService recruitService;

    @PostMapping("")
    public ResponseEntity<String> registerRecruit(@RequestBody RecruitRequestDTO dto) {
        recruitService.register(dto);

        return ResponseEntity.ok("채용공고 성공적으로 등록됐습니다.");
    }
}
