package com.example.englishapp.Security;

import com.example.englishapp.Dto.SpringBlogException;
import com.example.englishapp.Dto.StatusLogin;
import com.example.englishapp.Entity.TokenInfor;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Repository.TokeninforRepository;
import com.example.englishapp.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;


@Service
public class jwtToken {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokeninforRepository tokeninforRepository;

    private KeyStore keyStore;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/key.jks");
            keyStore.load(resourceAsStream, "khanhkma2001".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringBlogException("Exception occured while loading keystore");
        }

    }

    @Transactional
    public String generateToken(String username) {
        try {
            String tk = Jwts.builder()
                    .setIssuedAt(new Date())
                    .setSubject(username)
                    .signWith(getPrivateKey())
                    .compact();
            Claims claims = getBodyJwt(tk);
            TokenInfor tokenInfor = new TokenInfor(username, claims.getIssuedAt());
            System.out.println("ffffffffffff");
            tokeninforRepository.save(tokenInfor);
            return tk;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("mywebsite", "khanhkma2001".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
        }
    }

    //kiem tra post man
    public boolean validateToken(String jwt) {
        User user = userRepository.findByUsername(getUsernameFromJWT(jwt));
        if(!user.isBanned()) {
            Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
            Claims claims = getBodyJwt(jwt);
            TokenInfor tokenInfor = tokeninforRepository.findByUsernameAndDate(claims.getIssuedAt(), claims.getSubject()).orElseThrow();
            return true;
        }return false;
    }

    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("mywebsite").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
        }
    }

    public String getUsernameFromJWT(String token) {
        return getBodyJwt(token).getSubject();
    }

    public Claims getBodyJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(jwt)
                .getBody();
    }
}
