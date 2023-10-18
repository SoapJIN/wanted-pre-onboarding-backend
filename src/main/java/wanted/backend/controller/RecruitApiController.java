package wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PatchMapping("")
    public ResponseEntity<String> updateRecruit(@RequestBody RecruitRequestDTO dto) {
        recruitService.update(dto);
        return ResponseEntity.ok("채용공고 수정이 완료됐습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruit(@PathVariable("id") Long id) {
        recruitService.delete(id);
        return ResponseEntity.ok("채용공고 삭제가 완료됐습니다.");
    }
}
