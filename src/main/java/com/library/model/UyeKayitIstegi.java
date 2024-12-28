package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Yeni üye kayıt isteği modeli")
public class UyeKayitIstegi {
    
    @Schema(description = "Üyenin adı", example = "Ahmet", required = true)
    private String ad;
    
    @Schema(description = "Üyenin soyadı", example = "Yılmaz", required = true)
    private String soyad;
    
    @Schema(description = "E-posta adresi", example = "ahmet@email.com", required = true)
    private String eposta;
    
    @Schema(description = "Şifre", example = "guclu123sifre", required = true)
    private String sifre;
    
    @Schema(description = "Telefon numarası", example = "5551234567")
    private String telefon;
} 