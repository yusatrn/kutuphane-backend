package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "kitap_rezervasyonlari")
public class KitapRezervasyon {
    @Id
    private String id;
    private String kitapId;
    private String uyeId;
    private LocalDateTime rezervasyonTarihi;
    private LocalDateTime rezervasyonBitisTarihi;
    private RezvasyonDurumu durum;
    
    public enum RezvasyonDurumu {
        BEKLEMEDE,
        ONAYLANDI,
        IPTAL_EDILDI
    }
} 