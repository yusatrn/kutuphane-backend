package com.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
@Document(collection = "kitaplar")
@Schema(description = "Kitap modeli")
public class Kitap {
    
    @Id
    @Schema(description = "Kitap ID'si", example = "64a7b2d...")
    private String id;
    
    @Schema(description = "Kitap başlığı", example = "Suç ve Ceza")
    private String ad;
    
    @Schema(description = "Kitabın yazarı", example = "Fyodor Dostoyevski")
    private String yazar;
    
    @Schema(description = "ISBN numarası", example = "9789750719387")
    private String isbn;
    
    @Schema(description = "Yayınevi", example = "İş Bankası Kültür Yayınları")
    private String yayinevi;
    
    @Schema(description = "Yayın yılı", example = "2020")
    private Integer yayinYili;
    
    @Schema(description = "Kitabın kategorileri", example = "[\"roman\", \"klasik\"]")
    private List<String> kategoriler;
    
    @Schema(description = "Kitabın etiketleri", example = "[\"roman\", \"klasik\"]")
    private Set<String> etiketler;
    
    @Schema(description = "Benzer kitaplar", example = "[\"Suç ve Ceza\", \"İki Adam\"]")
    private List<String> benzerKitaplar;
    
    @Schema(description = "Stok durumu", example = "3")
    private Integer stokDurumu;
    
    @Schema(description = "Ortalama puan", example = "4.5")
    private Double ortalamaPuan;
    
    @Schema(description = "Konu özeti", example = "Suç ve Ceza hakkında detaylı bir açıklama")
    private String konuOzeti;
    
    @Schema(description = "Kitabın dili", example = "Türkçe")
    private String dil;
    
    @Schema(description = "Kategori ID'si", example = "64a7b2d...")
    private String kategoriId;
} 