package wanted.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.backend.dto.ApplyRequestDTO;
import wanted.backend.service.ApplyService;

@RestController
@RequestMapping("/api/applies")
@RequiredArgsConstructor
public class ApplyApiController {
    private final ApplyService applyService;

    @PostMapping("")
    public ResponseEntity<String> apply(@RequestBody ApplyRequestDTO applyRequestDTO) {
        applyService.register(applyRequestDTO);

        return ResponseEntity.ok("채용공고에 지원 성공했습니다.");
    }
}
