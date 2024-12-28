package com.library.controller;

import com.library.model.Duyuru;
import com.library.service.DuyuruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duyurular")
@RequiredArgsConstructor
public class DuyuruController {
    private final DuyuruService duyuruService;
    
    @PostMapping
    public ResponseEntity<Duyuru> duyuruEkle(@RequestBody Duyuru duyuru) {
        return ResponseEntity.ok(duyuruService.duyuruEkle(duyuru));
    }
    
    @GetMapping("/aktif")
    public ResponseEntity<List<Duyuru>> aktifDuyurular() {
        return ResponseEntity.ok(duyuruService.aktifDuyurular());
    }
    
    @GetMapping("/tip/{tip}")
    public ResponseEntity<List<Duyuru>> duyuruTipineGore(@PathVariable Duyuru.DuyuruTipi tip) {
        return ResponseEntity.ok(duyuruService.duyuruTipineGore(tip));
    }
} 