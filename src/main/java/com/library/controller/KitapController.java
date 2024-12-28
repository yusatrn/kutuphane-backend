package com.library.controller;

import com.library.model.Kitap;
import com.library.service.KitapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/kitaplar")
@Tag(name = "Kitap İşlemleri", description = "Kitap yönetimi için API uç noktaları")
@SecurityRequirement(name = "Bearer Authentication")
public class KitapController {

    @Autowired
    private KitapService kitapService;

    @Operation(
        summary = "Tüm Kitapları Getir",
        description = "Sistemdeki tüm kitapları listeler"
    )
    @ApiResponse(responseCode = "200", description = "Kitaplar başarıyla listelendi")
    @GetMapping
    public List<Kitap> tumKitaplariGetir() {
        return kitapService.tumKitaplariGetir();
    }

    @Operation(
        summary = "Yeni Kitap Ekle",
        description = "Sisteme yeni bir kitap ekler"
    )
    @ApiResponse(responseCode = "200", description = "Kitap başarıyla eklendi")
    @ApiResponse(responseCode = "400", description = "Geçersiz kitap bilgileri")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<Kitap> kitapEkle(@RequestBody Kitap kitap) {
        if (kitap.getAd() == null || kitap.getAd().trim().isEmpty()) {
            throw new IllegalArgumentException("Kitap adı boş olamaz");
        }
        
        if (kitap.getStokDurumu() < 0) {
            kitap.setStokDurumu(0);
        }
        
        return ResponseEntity.ok(kitapService.kitapKaydet(kitap));
    }

    @Operation(
        summary = "Kitap Getir",
        description = "ID'si verilen kitabı getirir"
    )
    @ApiResponse(responseCode = "200", description = "Kitap başarıyla getirildi")
    @ApiResponse(responseCode = "404", description = "Kitap bulunamadı")
    @GetMapping("/{id}")
    public ResponseEntity<Kitap> kitapGetir(
            @Parameter(description = "Getirilecek kitabın ID'si") 
            @PathVariable String id) {
        return kitapService.kitapGetir(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Kitap Güncelle",
        description = "Var olan bir kitabı günceller"
    )
    @ApiResponse(responseCode = "200", description = "Kitap başarıyla güncellendi")
    @ApiResponse(responseCode = "404", description = "Güncellenecek kitap bulunamadı")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<Kitap> kitapGuncelle(
            @Parameter(description = "Güncellenecek kitabın ID'si") 
            @PathVariable String id, 
            @RequestBody Kitap yeniKitap) {

        System.out.println("Gelen güncelleme isteği - ID: " + id); // Debug log
        System.out.println("Yeni kitap bilgileri: " + yeniKitap); // Debug log

        yeniKitap.setId(id); // ID'yi set et
        Kitap guncellenenKitap = kitapService.kitapGuncelle(id, yeniKitap);
        
        System.out.println("Güncellenen kitap: " + guncellenenKitap); // Debug log
        return ResponseEntity.ok(guncellenenKitap);
    }

    @Operation(
        summary = "Kitap Sil",
        description = "Belirtilen kitabı sistemden siler"
    )
    @ApiResponse(responseCode = "200", description = "Kitap başarıyla silindi")
    @ApiResponse(responseCode = "404", description = "Silinecek kitap bulunamadı")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> kitapSil(
            @Parameter(description = "Silinecek kitabın ID'si") 
            @PathVariable String id) {
        kitapService.kitapSil(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Ada Göre Kitap Ara",
        description = "Kitap adına göre arama yapar"
    )
    @ApiResponse(responseCode = "200", description = "Arama başarıyla tamamlandı")
    @GetMapping("/ara/ad/{ad}")
    public List<Kitap> adaGoreAra(
            @Parameter(description = "Aranacak kitap adı") 
            @PathVariable String ad) {
        return kitapService.adaGoreAra(ad);
    }

    @Operation(
        summary = "Yazara Göre Kitap Ara",
        description = "Kitap yazarına göre arama yapar"
    )
    @ApiResponse(responseCode = "200", description = "Arama başarıyla tamamlandı")
    @GetMapping("/ara/yazar/{yazar}")
    public List<Kitap> yazaraGoreAra(
            @Parameter(description = "Aranacak yazar adı") 
            @PathVariable String yazar) {
        return kitapService.yazaraGoreAra(yazar);
    }
} 