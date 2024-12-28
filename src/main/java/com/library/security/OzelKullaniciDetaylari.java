package com.library.security;

import com.library.model.Uye;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class OzelKullaniciDetaylari implements UserDetails {
    private final Uye uye;

    public OzelKullaniciDetaylari(Uye uye) {
        this.uye = uye;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return uye.getRoller().stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return uye.getSifre();
    }

    @Override
    public String getUsername() {
        return uye.getEposta();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return uye.getAktif();
    }
} 