package com.ead.authuser.configs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JwtProvider {
    @Value("${ead.auth.jwtexpiration}")
    private int expiration;
    @Value("${ead.auth.secretKey}")
    private String secretJwt;

    public String generateKey(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return Jwts.builder()
                .setSubject((userDetails.getUsername()))
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secretJwt).compact();
    }


}
