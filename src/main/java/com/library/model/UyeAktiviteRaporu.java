package com.library.model;

import lombok.Data;
import java.util.List;

@Data
public class UyeAktiviteRaporu {
    private String uyeId;
    private String uyeAdi;
    private Integer toplamOduncAlma;
    private Integer mevcutOduncAlma;
    private Double toplamGecikmeBedeli;
    private List<OduncAlma> oduncAlmaGecmisi;
} 