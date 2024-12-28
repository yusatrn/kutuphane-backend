package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "ceza_kayitlari")
public class CezaKaydi {
    @Id
    private String id;
    private String uyeId;
    private Double cezaMiktari;
    private LocalDateTime cezaTarihi;
    private String cezaNedeni;
    private boolean odendi;
} 