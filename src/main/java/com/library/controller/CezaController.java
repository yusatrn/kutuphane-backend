package com.library.controller;

import com.library.model.CezaKaydi;
import com.library.service.CezaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cezalar")
@RequiredArgsConstructor
public class CezaController {
    private final CezaService cezaService;
    
    @PostMapping
    public ResponseEntity<CezaKaydi> cezaEkle(@RequestBody CezaKaydi cezaKaydi) {
        return ResponseEntity.ok(cezaService.cezaKaydet(cezaKaydi));
    }
    
    @GetMapping("/uye/{uyeId}")
    public ResponseEntity<List<CezaKaydi>> uyeninCezalari(@PathVariable String uyeId) {
        return ResponseEntity.ok(cezaService.uyeninCezalari(uyeId));
    }
    
    @GetMapping("/odenmemis")
    public ResponseEntity<List<CezaKaydi>> odenmemisCezalar() {
        return ResponseEntity.ok(cezaService.odenmemisCezalar());
    }
} 