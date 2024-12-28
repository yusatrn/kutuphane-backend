package com.library.service;

import com.library.model.Uye;
import com.library.model.Rol;
import com.library.repository.UyeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import com.library.model.UyeGuncellemeIstegi;
import com.library.model.UyeAramaIstegi;

@Service
public class UyeService {

    @Autowired
    private UyeRepository uyeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Uye uyeKaydet(Uye uye) {
        if (uyeRepository.findByEposta(uye.getEposta()).isPresent()) {
            throw new RuntimeException("Bu email adresi zaten kayıtlı");
        }

        uye.setSifre(passwordEncoder.encode(uye.getSifre()));
        uye.setKayitTarihi(LocalDateTime.now());
        
        Set<Rol> roller = new HashSet<>();
        roller.add(Rol.UYE);
        uye.setRoller(roller);

        return uyeRepository.save(uye);
    }

    public Uye gorevliYap(String uyeId) {
        Uye uye = uyeRepository.findById(uyeId)
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        uye.getRoller().add(Rol.KUTUPHANE_GOREVLISI);
        return uyeRepository.save(uye);
    }

    public Uye adminYap(String uyeId) {
        Uye uye = uyeRepository.findById(uyeId)
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        uye.getRoller().add(Rol.ADMIN);
        return uyeRepository.save(uye);
    }

    public Uye uyePasifYap(String uyeId) {
        Uye uye = uyeRepository.findById(uyeId)
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        uye.setAktif(false);
        return uyeRepository.save(uye);
    }

    public List<Uye> tumGorevlileriGetir() {
        return uyeRepository.findByRoller(Rol.KUTUPHANE_GOREVLISI);
    }

    public void sonGirisTarihiniGuncelle(String eposta) {
        uyeRepository.findByEposta(eposta).ifPresent(uye -> {
            uye.setSonGirisTarihi(LocalDateTime.now());
            uyeRepository.save(uye);
        });
    }

    public Uye epostaIleGetir(String eposta) {
        return uyeRepository.findByEposta(eposta)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }

    public List<Uye> tumUyeleriGetir() {
        return uyeRepository.findAll();
    }

    public Optional<Uye> uyeGetir(String idOrEmail) {
        // Önce ID ile dene
        Optional<Uye> uye = uyeRepository.findById(idOrEmail);
        if (uye.isPresent()) {
            return uye;
        }
        // ID ile bulunamazsa email ile dene
        return uyeRepository.findByEposta(idOrEmail);
    }

    public List<Uye> uyeAra(UyeAramaIstegi istek) {
        // Temel sorgu oluştur
        List<Uye> sonuclar = uyeRepository.findAll();

        // Filtreleri uygula
        if (istek.getAd() != null && !istek.getAd().trim().isEmpty()) {
            sonuclar = sonuclar.stream()
                .filter(u -> u.getAd().toLowerCase().contains(istek.getAd().toLowerCase()))
                .toList();
        }
        if (istek.getSoyad() != null && !istek.getSoyad().trim().isEmpty()) {
            sonuclar = sonuclar.stream()
                .filter(u -> u.getSoyad().toLowerCase().contains(istek.getSoyad().toLowerCase()))
                .toList();
        }
        if (istek.getEposta() != null && !istek.getEposta().trim().isEmpty()) {
            sonuclar = sonuclar.stream()
                .filter(u -> u.getEposta().toLowerCase().contains(istek.getEposta().toLowerCase()))
                .toList();
        }
        if (istek.getTelefon() != null && !istek.getTelefon().trim().isEmpty()) {
            sonuclar = sonuclar.stream()
                .filter(u -> u.getTelefon().contains(istek.getTelefon()))
                .toList();
        }
        if (Boolean.TRUE.equals(istek.getSadeceMevcut())) {
            sonuclar = sonuclar.stream()
                .filter(Uye::getAktif)
                .toList();
        }

        return sonuclar;
    }

    public void sifreGuncelle(String uyeId, String eskiSifre, String yeniSifre) {
        Uye uye = uyeRepository.findById(uyeId)
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        // Eski şifreyi kontrol et
        if (!passwordEncoder.matches(eskiSifre, uye.getSifre())) {
            throw new RuntimeException("Eski şifre yanlış");
        }

        // Yeni şifreyi hashle ve kaydet
        uye.setSifre(passwordEncoder.encode(yeniSifre));
        uyeRepository.save(uye);
    }

    public Uye bilgileriGuncelle(UyeGuncellemeIstegi istek) {
        Uye uye = uyeRepository.findById(istek.getUyeId())
            .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        // Null olmayan alanları güncelle
        if (istek.getAd() != null && !istek.getAd().trim().isEmpty()) {
            uye.setAd(istek.getAd());
        }
        if (istek.getSoyad() != null && !istek.getSoyad().trim().isEmpty()) {
            uye.setSoyad(istek.getSoyad());
        }
        if (istek.getEposta() != null && !istek.getEposta().trim().isEmpty()) {
            // E-posta değişiyorsa, yeni e-postanın başka bir üyede olmadığından emin ol
            if (!uye.getEposta().equals(istek.getEposta()) && 
                uyeRepository.findByEposta(istek.getEposta()).isPresent()) {
                throw new RuntimeException("Bu e-posta adresi zaten kullanımda");
            }
            uye.setEposta(istek.getEposta());
        }
        if (istek.getTelefon() != null && !istek.getTelefon().trim().isEmpty()) {
            uye.setTelefon(istek.getTelefon());
        }

        return uyeRepository.save(uye);
    }
} 