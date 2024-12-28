package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "odunc_kitaplar")
public class OduncKitap {
    @Id
    private String id;
    private String uyeId;
    private String kitapId;
    private LocalDateTime oduncAlmaTarihi;
    private LocalDateTime iadeTarihi;
    private boolean aktif = true;
} 