package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Schema(description = "Başarılı giriş sonrası dönen JWT token modeli")
public class GirisCevabi {
    @Schema(description = "JWT kimlik doğrulama token'ı", 
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
} 