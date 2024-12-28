package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Üye isteği modeli")
public class UyeIstegi {
    @Schema(description = "İşlem yapılacak üyenin ID'si", required = true)
    private String uyeId;
}