package com.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "Ödünç alma detay modeli")
public class OduncAlmaDetay {
    
    @Schema(description = "Ödünç alma ID'si")
    private String id;
    
    @Schema(description = "Ödünç alma tarihi")
    private LocalDateTime oduncAlmaTarihi;
    
    @Schema(description = "İade tarihi")
    private LocalDateTime iadeTarihi;
    
    @Schema(description = "Aktif mi?")
    private boolean aktif;
    
    // Kitap bilgileri
    @Schema(description = "Kitap ID'si")
    private String kitapId;
    @Schema(description = "Kitap adı")
    private String kitapAdi;
    @Schema(description = "Yazar")
    private String yazar;
    @Schema(description = "ISBN")
    private String isbn;
    
    // Üye bilgileri
    @Schema(description = "Üye ID'si")
    private String uyeId;
    @Schema(description = "Üye adı")
    private String uyeAdi;
    @Schema(description = "Üye soyadı")
    private String uyeSoyadi;
    @Schema(description = "Üye e-posta")
    private String uyeEposta;
} 