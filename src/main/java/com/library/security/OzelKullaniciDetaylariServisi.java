package com.library.security;

import com.library.model.Uye;
import com.library.repository.UyeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OzelKullaniciDetaylariServisi implements UserDetailsService {
    
    private final UyeRepository uyeRepository;

    public OzelKullaniciDetaylariServisi(UyeRepository uyeRepository) {
        this.uyeRepository = uyeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String eposta) throws UsernameNotFoundException {
        Uye uye = uyeRepository.findByEposta(eposta)
            .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + eposta));
        
        return new OzelKullaniciDetaylari(uye);
    }
} 