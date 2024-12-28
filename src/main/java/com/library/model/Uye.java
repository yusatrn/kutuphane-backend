package com.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Document(collection = "uyeler")
@Schema(description = "Üye modeli")
public class Uye {
    
    @Id
    @Schema(description = "Üye ID'si", example = "64a7b2d...")
    private String id;
    
    @Schema(description = "Üyenin adı", example = "Ahmet")
    private String ad;
    
    @Schema(description = "Üyenin soyadı", example = "Yılmaz")
    private String soyad;
    
    @Schema(description = "E-posta adresi", example = "ahmet@email.com")
    private String eposta;
    
    @Schema(description = "Şifresi (hash'lenmiş)", example = "$2a$10$...")
    private String sifre;
    
    @Schema(description = "Telefon numarası", example = "5551234567")
    private String telefon;
    
    @Schema(description = "Üyelik rolleri", example = "[\"UYE\", \"KUTUPHANE_GOREVLISI\"]")
    private Set<Rol> roller;
    
    @Schema(description = "Üyelik durumu", example = "true")
    private Boolean aktif = true;
    
    @Schema(description = "Kayıt tarihi", example = "2024-03-15T10:30:00")
    private LocalDateTime kayitTarihi;
    
    @Schema(description = "Son giriş tarihi", example = "2024-03-15T15:45:00")
    private LocalDateTime sonGirisTarihi;
} 