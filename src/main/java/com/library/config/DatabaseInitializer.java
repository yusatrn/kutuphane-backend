package com.library.config;

import com.library.model.Uye;
import com.library.model.Rol;
import com.library.repository.UyeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UyeRepository uyeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Admin kullanıcısı yoksa oluştur
        if (uyeRepository.findByEposta("admin@kutuphane.com").isEmpty()) {
            Uye admin = new Uye();
            admin.setAd("Admin");
            admin.setSoyad("User");
            admin.setEposta("admin@kutuphane.com");
            admin.setSifre(passwordEncoder.encode("admin123")); // Güvenli bir şifre kullanın
            admin.setTelefon("5551234567");
            admin.setKayitTarihi(LocalDateTime.now());
            admin.setAktif(true);

            Set<Rol> roller = new HashSet<>();
            roller.add(Rol.ADMIN);
            admin.setRoller(roller);

            uyeRepository.save(admin);
            System.out.println("Admin kullanıcısı oluşturuldu!");
        }
    }
} 