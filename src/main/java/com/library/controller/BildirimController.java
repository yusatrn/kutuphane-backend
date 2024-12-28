package com.library.controller;

import com.library.model.Bildirim;
import com.library.service.BildirimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bildirimler")
@RequiredArgsConstructor
public class BildirimController {
    private final BildirimService bildirimService;
    
    @GetMapping("/uye/{uyeId}/okunmamis")
    public ResponseEntity<List<Bildirim>> okunmamisBildirimler(@PathVariable String uyeId) {
        return ResponseEntity.ok(bildirimService.okunmamisBildirimler(uyeId));
    }
    
    @PutMapping("/{bildirimId}/okundu")
    public ResponseEntity<Void> bildirimOkunduOlarakIsaretle(@PathVariable String bildirimId) {
        bildirimService.bildirimOkunduOlarakIsaretle(bildirimId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/uye/{uyeId}/okunmamis/sayisi")
    public ResponseEntity<Long> okunmamisBildirimSayisi(@PathVariable String uyeId) {
        return ResponseEntity.ok(bildirimService.okunmamisBildirimSayisi(uyeId));
    }
} 