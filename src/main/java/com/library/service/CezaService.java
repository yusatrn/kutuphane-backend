package com.library.service;

import com.library.model.CezaKaydi;
import com.library.repository.CezaKaydiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CezaService {
    private final CezaKaydiRepository cezaKaydiRepository;
    
    public CezaKaydi cezaKaydet(CezaKaydi cezaKaydi) {
        return cezaKaydiRepository.save(cezaKaydi);
    }
    
    public List<CezaKaydi> uyeninCezalari(String uyeId) {
        return cezaKaydiRepository.findByUyeId(uyeId);
    }
    
    public List<CezaKaydi> odenmemisCezalar() {
        return cezaKaydiRepository.findByOdendi(false);
    }
} 