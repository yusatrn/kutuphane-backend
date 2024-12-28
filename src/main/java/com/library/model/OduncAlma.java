package com.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "odunc_almalar")
@Schema(description = "Kitap ödünç alma işlemi modeli")
public class OduncAlma {
    
    @Id
    @Schema(description = "Ödünç alma kaydı ID'si", example = "64a7b2d...")
    private String id;
    
    @Schema(description = "Ödünç alınan kitabın ID'si", example = "64a7b2d...")
    private String kitapId;
    
    @Schema(description = "Ödünç alan üyenin ID'si", example = "64a7b2d...")
    private String uyeId;
    
    @Schema(description = "Ödünç alma tarihi", example = "2024-03-15T10:30:00")
    private LocalDateTime oduncAlmaTarihi;
    
    @Schema(description = "İade tarihi", example = "2024-03-30T10:30:00")
    private LocalDateTime iadeTarihi;
    
    @Schema(description = "Gerçek iade tarihi", example = "2024-03-28T14:20:00")
    private LocalDateTime gercekIadeTarihi;
    
    @Schema(description = "İade edildi mi?", example = "false")
    private Boolean iadeedildi = false;
    
    @Schema(description = "Gecikme bedeli", example = "10.5")
    private Double gecikmeBedeli = 0.0;
} 