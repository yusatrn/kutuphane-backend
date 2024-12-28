package com.library.controller;

import com.library.model.KitapRezervasyon;
import com.library.service.RezervasyonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rezervasyonlar")
@RequiredArgsConstructor
public class RezervasyonController {
    private final RezervasyonService rezervasyonService;
    
    @PostMapping
    public ResponseEntity<KitapRezervasyon> rezervasyonOlustur(@RequestBody KitapRezervasyon rezervasyon) {
        return ResponseEntity.ok(rezervasyonService.rezervasyonOlustur(rezervasyon));
    }
    
    @GetMapping("/kitap/{kitapId}")
    public ResponseEntity<List<KitapRezervasyon>> kitabinRezervasyonlari(@PathVariable String kitapId) {
        return ResponseEntity.ok(rezervasyonService.kitabinRezervasyonlari(kitapId));
    }
    
    @GetMapping("/uye/{uyeId}")
    public ResponseEntity<List<KitapRezervasyon>> uyeninRezervasyonlari(@PathVariable String uyeId) {
        return ResponseEntity.ok(rezervasyonService.uyeninRezervasyonlari(uyeId));
    }
} 