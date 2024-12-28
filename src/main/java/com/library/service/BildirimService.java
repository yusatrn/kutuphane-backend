package com.library.service;

import com.library.model.Bildirim;
import com.library.repository.BildirimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BildirimService {
    private final BildirimRepository bildirimRepository;
    
    public Bildirim bildirimOlustur(String uyeId, String baslik, String mesaj, Bildirim.BildirimTipi tip) {
        Bildirim bildirim = new Bildirim();
        bildirim.setUyeId(uyeId);
        bildirim.setBaslik(baslik);
        bildirim.setMesaj(mesaj);
        bildirim.setTip(tip);
        bildirim.setOlusturmaTarihi(LocalDateTime.now());
        bildirim.setOkundu(false);
        return bildirimRepository.save(bildirim);
    }
    
    public List<Bildirim> okunmamisBildirimler(String uyeId) {
        return bildirimRepository.findByUyeIdAndOkundu(uyeId, false);
    }
    
    public void bildirimOkunduOlarakIsaretle(String bildirimId) {
        bildirimRepository.findById(bildirimId).ifPresent(bildirim -> {
            bildirim.setOkundu(true);
            bildirimRepository.save(bildirim);
        });
    }
    
    public long okunmamisBildirimSayisi(String uyeId) {
        return bildirimRepository.countByUyeIdAndOkundu(uyeId, false);
    }
} 