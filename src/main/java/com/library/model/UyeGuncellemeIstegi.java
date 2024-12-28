package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Üye bilgileri güncelleme isteği modeli")
public class UyeGuncellemeIstegi {
    @Schema(description = "Üye ID'si", required = true)
    private String uyeId;
    
    @Schema(description = "Üyenin adı")
    private String ad;
    
    @Schema(description = "Üyenin soyadı")
    private String soyad;
    
    @Schema(description = "E-posta adresi")
    private String eposta;
    
    @Schema(description = "Telefon numarası")
    private String telefon;
}