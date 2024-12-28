package com.library.controller;

import com.library.model.Uye;
import com.library.model.UyeIstegi;
import com.library.model.SifreGuncellemeIstegi;
import com.library.model.UyeGuncellemeIstegi;
import com.library.model.UyeAramaIstegi;
import com.library.service.UyeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/uyeler")
@Tag(name = "Üye İşlemleri", description = "Üye yönetimi için API uç noktaları")
@SecurityRequirement(name = "Bearer Authentication")
public class UyeController {

    @Autowired
    private UyeService uyeService;

    @Operation(summary = "Üyeyi Görevli Yap")
    @PostMapping("/admin/gorevli-yap")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Uye> gorevliYap(@RequestBody UyeIstegi istek) {
        return ResponseEntity.ok(uyeService.gorevliYap(istek.getUyeId()));
    }

    @Operation(summary = "Üyeyi Admin Yap")
    @PostMapping("/admin/admin-yap")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Uye> adminYap(@RequestBody UyeIstegi istek) {
        return ResponseEntity.ok(uyeService.adminYap(istek.getUyeId()));
    }

    @Operation(summary = "Üyeyi Pasif Yap")
    @PostMapping("/admin/uye-pasif-yap")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Uye> uyePasifYap(@RequestBody UyeIstegi istek) {
        return ResponseEntity.ok(uyeService.uyePasifYap(istek.getUyeId()));
    }

    @Operation(summary = "Tüm Görevlileri Getir")
    @PostMapping("/admin/gorevliler")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Uye>> tumGorevlileriGetir() {
        return ResponseEntity.ok(uyeService.tumGorevlileriGetir());
    }

    @Operation(summary = "Üye Detayı Getir")
    @PostMapping("/detay")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<Uye> uyeDetay(@RequestBody UyeIstegi istek) {
        return uyeService.uyeGetir(istek.getUyeId())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Tüm Üyeleri Getir")
    @PostMapping("/listele")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<List<Uye>> tumUyeleriGetir() {
        return ResponseEntity.ok(uyeService.tumUyeleriGetir());
    }

    @Operation(summary = "Üye Şifre Güncelle")
    @PostMapping("/uyeler/sifre-guncelle")
    public ResponseEntity<?> sifreGuncelle(@RequestBody SifreGuncellemeIstegi istek) {
        uyeService.sifreGuncelle(istek.getUyeId(), istek.getEskiSifre(), istek.getYeniSifre());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Üye Bilgilerini Güncelle")
    @PostMapping("/uyeler/bilgi-guncelle")
    public ResponseEntity<Uye> bilgileriGuncelle(@RequestBody UyeGuncellemeIstegi istek) {
        return ResponseEntity.ok(uyeService.bilgileriGuncelle(istek));
    }

    @Operation(summary = "Üye Ara")
    @PostMapping("/uyeler/ara")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<List<Uye>> uyeAra(@RequestBody UyeAramaIstegi istek) {
        return ResponseEntity.ok(uyeService.uyeAra(istek));
    }
} 