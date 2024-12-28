package com.library.controller;

import com.library.model.Kategori;
import com.library.service.KategoriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/kategoriler")
@Tag(name = "Kategori İşlemleri", description = "Kategori yönetimi için API uç noktaları")
@SecurityRequirement(name = "Bearer Authentication")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @Operation(summary = "Tüm Kategorileri Getir", description = "Sistemdeki tüm kategorileri listeler")
    @GetMapping
    public List<Kategori> tumKategorileriGetir() {
        return kategoriService.tumKategorileriGetir();
    }

    @Operation(summary = "Yeni Kategori Ekle", description = "Sisteme yeni bir kategori ekler")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public Kategori kategoriEkle(@RequestBody Kategori kategori) {
        return kategoriService.kategoriKaydet(kategori);
    }

    @Operation(summary = "Kategori Güncelle", description = "Var olan bir kategoriyi günceller")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
    public ResponseEntity<Kategori> kategoriGuncelle(@PathVariable String id, @RequestBody Kategori kategori) {
        return ResponseEntity.ok(kategoriService.kategoriGuncelle(id, kategori));
    }

    @Operation(summary = "Kategori Sil", description = "Belirtilen kategoriyi siler")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> kategoriSil(@PathVariable String id) {
        kategoriService.kategoriSil(id);
        return ResponseEntity.ok().build();
    }
} 