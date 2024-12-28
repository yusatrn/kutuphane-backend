package com.library.security;

import com.library.model.Uye;
import com.library.service.UyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UyeService uyeService;

    @Override
    public UserDetails loadUserByUsername(String eposta) throws UsernameNotFoundException {
        Uye uye = uyeService.epostaIleGetir(eposta);
        
        return User.builder()
            .username(uye.getEposta())
            .password(uye.getSifre())
            .authorities(uye.getRoller().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name()))
                .collect(Collectors.toList()))
            .build();
    }
} 