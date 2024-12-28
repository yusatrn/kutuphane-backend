package com.library.controller;

import com.library.model.KitapOduncIstatistik;
import com.library.model.GecikmeRaporu;
import com.library.service.RaporService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/raporlar")
@Tag(name = "Raporlama İşlemleri", description = "Kütüphane raporlama işlemleri için API uç noktaları")
@PreAuthorize("hasAnyRole('ADMIN', 'KUTUPHANE_GOREVLISI')")
@SecurityRequirement(name = "Bearer Authentication")
public class RaporController {

    @Autowired
    private RaporService raporService;

    @Operation(
        summary = "En Çok Okunan Kitaplar",
        description = "En çok ödünç alınan kitapların listesini getirir"
    )
    @ApiResponse(responseCode = "200", description = "Rapor başarıyla oluşturuldu")
    @GetMapping("/en-cok-okunan")
    public List<KitapOduncIstatistik> enCokOkunanKitaplar() {
        return raporService.getEnCokOduncAlinanKitaplar();
    }

    @Operation(
        summary = "Gecikmeli Kitaplar",
        description = "İade tarihi geçmiş kitapların listesini getirir"
    )
    @ApiResponse(responseCode = "200", description = "Rapor başarıyla oluşturuldu")
    @GetMapping("/gecikmeli-kitaplar")
    public List<GecikmeRaporu> gecikenKitaplar() {
        return raporService.getGecikmeliKitaplar();
    }
} 