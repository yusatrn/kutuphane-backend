package com.library.controller;

import com.library.model.KitapDegerlendirme;
import com.library.service.DegerlendirmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/degerlendirmeler")
@RequiredArgsConstructor
public class DegerlendirmeController {
    private final DegerlendirmeService degerlendirmeService;
    
    @PostMapping
    public ResponseEntity<KitapDegerlendirme> degerlendirmeEkle(@RequestBody KitapDegerlendirme degerlendirme) {
        return ResponseEntity.ok(degerlendirmeService.degerlendirmeEkle(degerlendirme));
    }
    
    @GetMapping("/kitap/{kitapId}")
    public ResponseEntity<List<KitapDegerlendirme>> kitabinDegerlendirmeleri(@PathVariable String kitapId) {
        return ResponseEntity.ok(degerlendirmeService.kitabinDegerlendirmeleri(kitapId));
    }
    
    @GetMapping("/kitap/{kitapId}/ortalama")
    public ResponseEntity<Double> kitabinOrtalamaPuani(@PathVariable String kitapId) {
        return ResponseEntity.ok(degerlendirmeService.kitabinOrtalamaPuani(kitapId));
    }
} 