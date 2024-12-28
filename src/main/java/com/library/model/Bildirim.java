package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "bildirimler")
public class Bildirim {
    @Id
    private String id;
    private String uyeId;
    private String baslik;
    private String mesaj;
    private LocalDateTime olusturmaTarihi;
    private boolean okundu;
    private BildirimTipi tip;
    
    public enum BildirimTipi {
        TESLIM_HATIRLATMA,
        REZERVASYON_ONAY,
        YENI_KITAP,
        CEZA_BILDIRIMI,
        SISTEM_BILDIRIMI
    }
} 