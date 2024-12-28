package com.library.service;

import com.library.model.*;
import com.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Service
public class RaporService {
    @Autowired
    private OduncAlmaRepository oduncAlmaRepository;
    
    @Autowired
    private KitapRepository kitapRepository;
    
    @Autowired
    private UyeRepository uyeRepository;

    public List<KitapOduncIstatistik> getEnCokOduncAlinanKitaplar() {
        List<OduncAlma> tumOduncAlmalar = oduncAlmaRepository.findAll();
        Map<String, KitapOduncIstatistik> istatistikMap = new HashMap<>();
        
        for (OduncAlma oduncAlma : tumOduncAlmalar) {
            Kitap kitap = kitapRepository.findById(oduncAlma.getKitapId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
                
            istatistikMap.computeIfAbsent(kitap.getId(), k -> {
                KitapOduncIstatistik istatistik = new KitapOduncIstatistik();
                istatistik.setKitapId(kitap.getId());
                istatistik.setAd(kitap.getAd());
                istatistik.setYazar(kitap.getYazar());
                istatistik.setOduncAlmaSayisi(0L);
                istatistik.setOrtalamaSure(0.0);
                return istatistik;
            });
            
            KitapOduncIstatistik istatistik = istatistikMap.get(kitap.getId());
            istatistik.setOduncAlmaSayisi(istatistik.getOduncAlmaSayisi() + 1);
        }
        
        return istatistikMap.values().stream()
            .sorted((a, b) -> b.getOduncAlmaSayisi().compareTo(a.getOduncAlmaSayisi()))
            .limit(10)
            .toList();
    }

    public List<GecikmeRaporu> getGecikmeliKitaplar() {
        List<OduncAlma> tumAktifOdunclar = oduncAlmaRepository.findByIadeedildi(false);
        List<GecikmeRaporu> raporlar = new ArrayList<>();
        
        for (OduncAlma oduncAlma : tumAktifOdunclar) {
            Kitap kitap = kitapRepository.findById(oduncAlma.getKitapId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
                
            Uye uye = uyeRepository.findById(oduncAlma.getUyeId())
                .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));
            
            GecikmeRaporu rapor = new GecikmeRaporu();
            rapor.setKitapId(kitap.getId());
            rapor.setAd(kitap.getAd());
            rapor.setUyeId(uye.getId());
            rapor.setUyeAdi(uye.getAd() + " " + uye.getSoyad());
            rapor.setIadeTarihi(oduncAlma.getIadeTarihi());
            
            if (LocalDateTime.now().isAfter(oduncAlma.getIadeTarihi())) {
                rapor.setGecikenGun(Duration.between(oduncAlma.getIadeTarihi(), LocalDateTime.now()).toDays());
                rapor.setGecikmeBedeli(rapor.getGecikenGun() * 1.0);
            } else {
                rapor.setGecikenGun(0L);
                rapor.setGecikmeBedeli(0.0);
            }
            
            raporlar.add(rapor);
        }
        
        return raporlar;
    }

    public UyeAktiviteRaporu getUyeAktiviteRaporu(String uyeId) {
        Uye uye = uyeRepository.findById(uyeId)
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));
            
        List<OduncAlma> uyeOduncAlmalari = oduncAlmaRepository.findByUyeId(uyeId);
        
        UyeAktiviteRaporu rapor = new UyeAktiviteRaporu();
        rapor.setUyeId(uye.getId());
        rapor.setUyeAdi(uye.getAd() + " " + uye.getSoyad());
        rapor.setToplamOduncAlma(uyeOduncAlmalari.size());
        rapor.setMevcutOduncAlma((int) uyeOduncAlmalari.stream()
            .filter(b -> !b.getIadeedildi())
            .count());
        rapor.setToplamGecikmeBedeli(uyeOduncAlmalari.stream()
            .mapToDouble(b -> b.getGecikmeBedeli() != null ? b.getGecikmeBedeli() : 0.0)
            .sum());
        rapor.setOduncAlmaGecmisi(uyeOduncAlmalari);
        
        return rapor;
    }
} 