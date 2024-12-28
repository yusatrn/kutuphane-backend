package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Şifre güncelleme isteği modeli")
public class SifreGuncellemeIstegi {
    @Schema(description = "Üye ID'si", required = true)
    private String uyeId;
    
    @Schema(description = "Eski şifre", required = true)
    private String eskiSifre;
    
    @Schema(description = "Yeni şifre", required = true)
    private String yeniSifre;
}