package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Üye arama isteği modeli")
public class UyeAramaIstegi {
    @Schema(description = "Ad ile arama")
    private String ad;
    
    @Schema(description = "Soyad ile arama")
    private String soyad;
    
    @Schema(description = "E-posta ile arama")
    private String eposta;
    
    @Schema(description = "Telefon ile arama")
    private String telefon;
    
    @Schema(description = "Sadece aktif üyeleri getir")
    private Boolean sadeceMevcut;
}