package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Kitap ödünç alma istatistikleri modeli")
public class KitapOduncIstatistik {
    
    @Schema(description = "Kitap ID'si", example = "64a7b2d...")
    private String kitapId;
    
    @Schema(description = "Kitap başlığı", example = "Suç ve Ceza")
    private String ad;
    
    @Schema(description = "Kitabın yazarı", example = "Fyodor Dostoyevski")
    private String yazar;
    
    @Schema(description = "Toplam ödünç alınma sayısı", example = "25")
    private Long oduncAlmaSayisi;
    
    @Schema(description = "Ortalama okuma süresi (gün)", example = "12.5")
    private Double ortalamaSure;
} 