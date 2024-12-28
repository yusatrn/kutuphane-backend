package com.library.service;

import com.library.model.Kitap;
import com.library.repository.KitapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class KitapService {

    @Autowired
    private KitapRepository kitapRepository;

    public List<Kitap> tumKitaplariGetir() {
        return kitapRepository.findAll();
    }

    @Transactional
    public Kitap kitapKaydet(Kitap kitap) {
        return kitapRepository.save(kitap);
    }

    @Transactional
    public Kitap kitapGuncelle(String id, Kitap yeniKitap) {
        System.out.println("Servis - Gelen kitap: " + yeniKitap); // Debug log
        
        Kitap mevcutKitap = kitapRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        // Tüm alanları güncelle
        mevcutKitap.setAd(yeniKitap.getAd());
        mevcutKitap.setYazar(yeniKitap.getYazar());
        mevcutKitap.setIsbn(yeniKitap.getIsbn());
        mevcutKitap.setYayinevi(yeniKitap.getYayinevi());
        mevcutKitap.setYayinYili(yeniKitap.getYayinYili());
        mevcutKitap.setStokDurumu(yeniKitap.getStokDurumu());
        mevcutKitap.setKategoriId(yeniKitap.getKategoriId());

        Kitap guncellenenKitap = kitapRepository.save(mevcutKitap);
        System.out.println("Servis - Güncellenen kitap: " + guncellenenKitap); // Debug log
        
        return guncellenenKitap;
    }

    public void kitapSil(String id) {
        kitapRepository.deleteById(id);
    }

    public List<Kitap> adaGoreAra(String ad) {
        return kitapRepository.findByAdContainingIgnoreCase(ad);
    }

    public List<Kitap> yazaraGoreAra(String yazar) {
        return kitapRepository.findByYazarContainingIgnoreCase(yazar);
    }

    public Optional<Kitap> kitapGetir(String id) {
        return kitapRepository.findById(id);
    }
} 