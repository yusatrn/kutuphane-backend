package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Kullanıcı rolleri")
public enum Rol {
    @Schema(description = "Normal üye rolü")
    UYE,
    
    @Schema(description = "Kütüphane görevlisi rolü")
    KUTUPHANE_GOREVLISI,
    
    @Schema(description = "Admin rolü")
    ADMIN
} 