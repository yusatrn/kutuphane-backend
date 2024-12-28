package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "Gecikmiş kitap raporu modeli")
public class GecikmeRaporu {
    
    @Schema(description = "Kitap ID'si", example = "64a7b2d...")
    private String kitapId;
    
    @Schema(description = "Kitap başlığı", example = "Suç ve Ceza")
    private String ad;
    
    @Schema(description = "Üye ID'si", example = "64a7b2d...")
    private String uyeId;
    
    @Schema(description = "Üye adı soyadı", example = "Ahmet Yılmaz")
    private String uyeAdi;
    
    @Schema(description = "İade tarihi", example = "2024-03-15T10:30:00")
    private LocalDateTime iadeTarihi;
    
    @Schema(description = "Geciken gün sayısı", example = "5")
    private Long gecikenGun;
    
    @Schema(description = "Gecikme bedeli", example = "25.0")
    private Double gecikmeBedeli;
} 