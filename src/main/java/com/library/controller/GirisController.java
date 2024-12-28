package com.library.controller;

import com.library.model.GirisIstegi;
import com.library.model.GirisCevabi;
import com.library.model.Uye;
import com.library.model.UyeKayitIstegi;
import com.library.model.Rol;
import com.library.security.JwtUtil;
import com.library.service.UyeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Kimlik Doğrulama", description = "Kimlik doğrulama işlemleri için API uç noktaları")
public class GirisController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UyeService uyeService;

    @Operation(
        summary = "Kullanıcı Girişi",
        description = "E-posta ve şifre ile giriş yaparak JWT token alır",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                schema = @Schema(implementation = GirisIstegi.class),
                mediaType = "application/json",
                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                    value = """
                    {
                        "eposta": "ornek@email.com",
                        "sifre": "sifre123"
                    }
                    """
                )
            )
        )
    )
    @ApiResponse(
        responseCode = "200",
        description = "Başarılı giriş",
        content = @Content(schema = @Schema(implementation = GirisCevabi.class))
    )
    @ApiResponse(responseCode = "401", description = "Geçersiz kimlik bilgileri")
    @PostMapping(value = "/giris", consumes = "application/json")
    public ResponseEntity<?> giris(@RequestBody GirisIstegi girisIstegi) {
        try {
            System.out.println("Giriş isteği: " + girisIstegi.getEposta());

            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    girisIstegi.getEposta(), 
                    girisIstegi.getSifre()
                )
            );

            Uye uye = uyeService.epostaIleGetir(girisIstegi.getEposta());
            String token = jwtUtil.tokenOlustur(uye.getEposta(), 
                uye.getRoller().stream()
                    .map(Rol::name)
                    .collect(Collectors.toSet()));

            uyeService.sonGirisTarihiniGuncelle(uye.getEposta());
            
            return ResponseEntity.ok(new GirisCevabi(token));
        } catch (Exception e) {
            System.out.println("Giriş hatası: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Kimlik doğrulama başarısız: " + e.getMessage());
        }
    }

    @Operation(
        summary = "Yeni Üye Kaydı",
        description = "Sisteme yeni bir üye kaydeder"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Başarılı kayıt",
        content = @Content(schema = @Schema(implementation = Uye.class))
    )
    @ApiResponse(responseCode = "400", description = "Geçersiz üye bilgileri")
    @PostMapping("/kayit")
    public ResponseEntity<?> kayit(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Yeni üye bilgileri",
                required = true,
                content = @Content(schema = @Schema(implementation = UyeKayitIstegi.class))
            )
            @org.springframework.web.bind.annotation.RequestBody UyeKayitIstegi kayitIstegi) {
        try {
            Uye uye = new Uye();
            uye.setAd(kayitIstegi.getAd());
            uye.setSoyad(kayitIstegi.getSoyad());
            uye.setEposta(kayitIstegi.getEposta());
            uye.setSifre(kayitIstegi.getSifre());
            uye.setTelefon(kayitIstegi.getTelefon());
            
            Uye yeniUye = uyeService.uyeKaydet(uye);
            return ResponseEntity.ok(yeniUye);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Kayıt başarısız: " + e.getMessage());
        }
    }
} 