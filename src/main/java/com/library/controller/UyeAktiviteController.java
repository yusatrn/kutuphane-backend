package com.library.controller;

import com.library.model.UyeAktivite;
import com.library.service.UyeAktiviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/aktiviteler")
@RequiredArgsConstructor
public class UyeAktiviteController {
    private final UyeAktiviteService aktiviteService;
    
    @GetMapping("/uye/{uyeId}")
    public ResponseEntity<List<UyeAktivite>> uyeninAktiviteleri(@PathVariable String uyeId) {
        return ResponseEntity.ok(aktiviteService.uyeninAktiviteleri(uyeId));
    }
    
    @GetMapping("/uye/{uyeId}/tarih")
    public ResponseEntity<List<UyeAktivite>> uyeninTarihAraligiAktiviteleri(
            @PathVariable String uyeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime baslangic,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime bitis) {
        return ResponseEntity.ok(aktiviteService.uyeninTarihAraligiAktiviteleri(uyeId, baslangic, bitis));
    }
} 