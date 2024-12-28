package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ödünç alma isteği modeli")
public class OduncAlmaIstegi {
    @Schema(description = "Ödünç alınacak kitabın ID'si", required = true)
    private String kitapId;
    
    @Schema(description = "Ödünç alan üyenin ID'si", required = true)
    private String uyeId;
    
    @Schema(description = "Ödünç alma süresi (gün)", example = "7")
    private Integer gunSayisi;
}