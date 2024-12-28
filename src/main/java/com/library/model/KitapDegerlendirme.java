package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "kitap_degerlendirmeleri")
public class KitapDegerlendirme {
    @Id
    private String id;
    private String kitapId;
    private String uyeId;
    private Integer puan;
    private String yorum;
    private LocalDateTime degerlendirmeTarihi;
} 