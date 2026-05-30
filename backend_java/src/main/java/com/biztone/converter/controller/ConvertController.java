package com.biztone.converter.controller;

import com.biztone.converter.model.ConvertRequest;
import com.biztone.converter.model.ConvertResponse;
import com.biztone.converter.service.ToneConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ConvertController {

    private final ToneConverterService toneConverterService;

    public ConvertController(ToneConverterService toneConverterService) {
        this.toneConverterService = toneConverterService;
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody ConvertRequest request) {
        try {
            String converted = toneConverterService.convert(request.getText(), request.getTarget_audience());
            return ResponseEntity.ok(new ConvertResponse(
                converted,
                request.getTarget_audience(),
                request.getText()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("detail", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("detail", "AI 변환 중 오류가 발생했습니다."));
        }
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }
}
