package com.library.service;

import com.library.model.Kategori;
import com.library.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    public List<Kategori> tumKategorileriGetir() {
        return kategoriRepository.findAll();
    }

    public Kategori kategoriKaydet(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }

    public Kategori kategoriGuncelle(String id, Kategori kategori) {
        if (kategoriRepository.existsById(id)) {
            kategori.setId(id);
            return kategoriRepository.save(kategori);
        }
        throw new RuntimeException("Kategori bulunamadı");
    }

    public void kategoriSil(String id) {
        kategoriRepository.deleteById(id);
    }
} 