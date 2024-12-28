package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "duyurular")
public class Duyuru {
    @Id
    private String id;
    private String baslik;
    private String icerik;
    private LocalDateTime yayinTarihi;
    private LocalDateTime sonGecerlilikTarihi;
    private DuyuruTipi tip;
    
    public enum DuyuruTipi {
        GENEL,
        BAKIM,
        YENI_KITAP,
        ETKINLIK
    }
} 