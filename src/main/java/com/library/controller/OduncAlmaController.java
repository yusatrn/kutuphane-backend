package com.library.controller;

import com.library.model.IadeIstegi;
import com.library.model.OduncAlma;
import com.library.model.OduncAlmaDetay;
import com.library.model.OduncAlmaIstegi;
import com.library.model.UyeIstegi;
import com.library.service.OduncAlmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/odunc")
@Tag(name = "Ödünç İşlemleri", description = "Kitap ödünç alma/verme işlemleri için API uç noktaları")
@SecurityRequirement(name = "Bearer Authentication")
public class OduncAlmaController {

    @Autowired
    private OduncAlmaService oduncAlmaService;

    @Operation(summary = "Tüm Ödünç Kayıtlarını Listele")
    @GetMapping("/listele")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<List<OduncAlmaDetay>> tumOduncleriGetir() {
        return ResponseEntity.ok(oduncAlmaService.tumOduncAlmalar());
    }

    @Operation(summary = "Kitap Ödünç Ver")
    @PostMapping("/ver")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<OduncAlma> oduncVer(@RequestBody OduncAlmaIstegi istek) {
        System.out.println("Ödünç verme isteği: " + istek); // Debug log
        OduncAlma oduncAlma = oduncAlmaService.kitapOduncAl(
            istek.getKitapId(), 
            istek.getUyeId(), 
            istek.getGunSayisi()
        );
        return ResponseEntity.ok(oduncAlma);
    }

    @Operation(summary = "Kitap İade Al")
    @PostMapping("/iade")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<OduncAlma> iadeAl(@RequestBody IadeIstegi istek) {
        System.out.println("İade isteği: " + istek); // Debug log
        OduncAlma oduncAlma = oduncAlmaService.kitapIadeEt(istek.getOduncId());
        return ResponseEntity.ok(oduncAlma);
    }

    @Operation(summary = "Üyenin Ödünç Kayıtlarını Getir")
    @PostMapping("/uye/listele")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI', 'UYE')")
    public ResponseEntity<List<OduncAlmaDetay>> uyeninOduncleriniGetir(@RequestBody UyeIstegi istek) {
        return ResponseEntity.ok(oduncAlmaService.uyeninOduncAlmalari(istek.getUyeId()));
    }

    @Operation(summary = "Üyenin Aktif Ödünç Kayıtlarını Getir")
    @PostMapping("/uye/aktif")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI', 'UYE')")
    public ResponseEntity<List<OduncAlmaDetay>> uyeninAktifOduncleriniGetir(@RequestBody UyeIstegi istek) {
        return ResponseEntity.ok(oduncAlmaService.aktifOduncAlmalar(istek.getUyeId()));
    }
} 