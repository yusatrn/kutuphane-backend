package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Listeleme isteği modeli")
public class ListelemeIstegi {
    @Schema(description = "Sadece aktif ödünç almaları getir")
    private Boolean sadeceMevcut;
}