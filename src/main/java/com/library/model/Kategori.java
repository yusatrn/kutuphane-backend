package com.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Document(collection = "kategoriler")
@Schema(description = "Kitap kategorisi modeli")
public class Kategori {
    
    @Id
    @Schema(description = "Kategori ID'si", example = "64a7b2d...")
    
    private String id;
    
    @Schema(description = "Kategori adı", example = "Roman")
    private String ad;
    
    @Schema(description = "Kategori açıklaması", example = "Roman türündeki kitaplar")
    private String aciklama;
} 