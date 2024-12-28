package com.library.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Component
public class JwtFiltre extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // CORS Preflight isteklerini bypass et
            if (request.getMethod().equals("OPTIONS")) {
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                if (jwtUtil.tokenGecerliMi(token)) {
                    String eposta = jwtUtil.epostaGetir(token);
                    Set<String> roller = jwtUtil.getRolesFromToken(token);

                    List<GrantedAuthority> authorities = roller.stream()
                            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(eposta, null,
                            authorities);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    protected boolean shouldNotFilter(@SuppressWarnings("null") HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/") ||
                path.equals("/api/health") ||
                path.startsWith("/swagger-ui/") ||
                path.startsWith("/api-docs/");
    }
}