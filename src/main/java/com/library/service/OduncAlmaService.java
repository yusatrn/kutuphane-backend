package com.library.service;

import com.library.model.*;
import com.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OduncAlmaService {
    
    @Autowired
    private OduncAlmaRepository oduncAlmaRepository;
    
    @Autowired
    private KitapService kitapService;
    
    @Autowired
    private UyeService uyeService;

    @Transactional
    public OduncAlma kitapOduncAl(String kitapId, String uyeId, Integer gunSayisi) {
        System.out.println("Ödünç alma servisi - kitapId: " + kitapId + ", uyeId: " + uyeId); // Debug log
        
        Kitap kitap = kitapService.kitapGetir(kitapId)
            .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
            
        if (kitap.getStokDurumu() <= 0) {
            throw new RuntimeException("Kitap stokta yok");
        }

        Uye uye = uyeService.uyeGetir(uyeId)
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));
        if (!uye.getAktif()) {
            throw new RuntimeException("Pasif üyeler kitap ödünç alamaz");
        }

        OduncAlma oduncAlma = new OduncAlma();
        oduncAlma.setKitapId(kitapId);
        oduncAlma.setUyeId(uyeId);
        oduncAlma.setOduncAlmaTarihi(LocalDateTime.now());
        oduncAlma.setIadeTarihi(LocalDateTime.now().plusDays(gunSayisi != null ? gunSayisi : 15));
        oduncAlma.setIadeedildi(false);

        // Stok güncelleme
        kitap.setStokDurumu(kitap.getStokDurumu() - 1);
        kitapService.kitapKaydet(kitap);

        return oduncAlmaRepository.save(oduncAlma);
    }

    @Transactional
    public OduncAlma kitapIadeEt(String oduncId) {
        System.out.println("İade servisi - oduncId: " + oduncId); // Debug log
        
        OduncAlma oduncAlma = oduncAlmaRepository.findById(oduncId)
            .orElseThrow(() -> new RuntimeException("Ödünç alma kaydı bulunamadı"));

        if (oduncAlma.getIadeedildi()) {
            throw new RuntimeException("Bu kitap zaten iade edilmiş");
        }

        // Stok güncelleme
        Kitap kitap = kitapService.kitapGetir(oduncAlma.getKitapId())
            .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        kitap.setStokDurumu(kitap.getStokDurumu() + 1);
        kitapService.kitapKaydet(kitap);

        oduncAlma.setIadeedildi(true);
        oduncAlma.setGercekIadeTarihi(LocalDateTime.now());

        return oduncAlmaRepository.save(oduncAlma);
    }

    public List<OduncAlmaDetay> uyeninOduncAlmalari(String uyeId) {
        List<OduncAlma> odunclar = oduncAlmaRepository.findByUyeId(uyeId);
        return odunclar.stream()
            .map(this::oduncAlmaDetayOlustur)
            .collect(Collectors.toList());
    }

    public List<OduncAlmaDetay> aktifOduncAlmalar(String uyeId) {
        List<OduncAlma> oduncAlmalar = oduncAlmaRepository.findByUyeIdAndIadeedildi(uyeId, false);
        return oduncAlmalar.stream()
            .map(this::oduncAlmaDetayOlustur)
            .collect(Collectors.toList());
    }

    public List<OduncAlmaDetay> tumOduncAlmalar() {
        List<OduncAlma> odunclar = oduncAlmaRepository.findAll();
        return odunclar.stream()
            .map(this::oduncAlmaDetayOlustur)
            .collect(Collectors.toList());
    }

    public List<OduncAlmaDetay> tumAktifOduncAlmalar() {
        List<OduncAlma> oduncAlmalar = oduncAlmaRepository.findByIadeedildi(false);
        return oduncAlmalar.stream()
            .map(this::oduncAlmaDetayOlustur)
            .collect(Collectors.toList());
    }

    private OduncAlmaDetay oduncAlmaDetayOlustur(OduncAlma odunc) {
        OduncAlmaDetay detay = new OduncAlmaDetay();
        detay.setId(odunc.getId());
        detay.setOduncAlmaTarihi(odunc.getOduncAlmaTarihi());
        detay.setIadeTarihi(odunc.getIadeTarihi());
        detay.setAktif(!odunc.getIadeedildi());

        // Debug log ekleyelim
        System.out.println("Kitap ID: " + odunc.getKitapId());

        // Kitap bilgilerini ekle
        kitapService.kitapGetir(odunc.getKitapId()).ifPresent(k -> {
            System.out.println("Bulunan Kitap: " + k.getAd()); // Debug log
            detay.setKitapId(k.getId());
            detay.setKitapAdi(k.getAd());
            detay.setYazar(k.getYazar());
            detay.setIsbn(k.getIsbn());
        });

        // Üye bilgilerini ekle
        uyeService.uyeGetir(odunc.getUyeId()).ifPresent(uye -> {
            detay.setUyeId(uye.getId());
            detay.setUyeAdi(uye.getAd());
            detay.setUyeSoyadi(uye.getSoyad());
            detay.setUyeEposta(uye.getEposta());
        });

        // Son durumu kontrol edelim
        System.out.println("Oluşturulan Detay - Kitap Adı: " + detay.getKitapAdi());

        return detay;
    }
} 