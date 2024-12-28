package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "uye_aktiviteleri")
public class UyeAktivite {
    @Id
    private String id;
    private String uyeId;
    private String islemTipi; // ODUNC_ALMA, TESLIM, REZERVASYON, vb.
    private String kitapId;
    private LocalDateTime islemTarihi;
    private String detay;
} 