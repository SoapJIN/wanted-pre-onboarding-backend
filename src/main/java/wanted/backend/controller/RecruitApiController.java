package wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.backend.dto.RecruitDetailResponseDTO;
import wanted.backend.dto.RecruitRequestDTO;
import wanted.backend.dto.RecruitResponseDTO;
import wanted.backend.service.RecruitService;

import java.util.List;

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

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateRecruit(@PathVariable("id") Long id, @RequestBody RecruitRequestDTO dto) {
        recruitService.update(id, dto);
        return ResponseEntity.ok("채용공고 수정이 완료됐습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruit(@PathVariable("id") Long id) {
        recruitService.delete(id);
        return ResponseEntity.ok("채용공고 삭제가 완료됐습니다.");
    }

    @GetMapping("")
    public ResponseEntity<List<RecruitResponseDTO>> search(@RequestParam(value="keyword", required = false) String keyword){
        List<RecruitResponseDTO> list;

        if(keyword != null) {
            list = recruitService.getListByKeyword(keyword);
        }else {
            list = recruitService.getList();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruitDetailResponseDTO> getRecruitDetail(@PathVariable("id")Long id) {
        RecruitDetailResponseDTO dto = recruitService.getDetail(id);

        return ResponseEntity.ok(dto);
    }
}
