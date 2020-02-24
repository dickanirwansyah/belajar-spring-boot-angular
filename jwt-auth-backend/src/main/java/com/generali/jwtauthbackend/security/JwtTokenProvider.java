package com.generali.jwtauthbackend.security;

import com.generali.jwtauthbackend.constant.AllMessageConstant;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMS;

    /** generate token **/
    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpirationInMS);

        return Jwts.builder()
                .setSubject(String.valueOf(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /** user id from jwt **/
    public int getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    /** validate token **/
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            log.error(e.getMessage() + AllMessageConstant.JwtSignatureInvalidMessage);
        }catch (MalformedJwtException e){
            log.error(e.getMessage() + AllMessageConstant.JwtMalformedMessage);
        }catch (ExpiredJwtException e){
            log.error(e.getMessage() + AllMessageConstant.JwtExpirationMessage);
        }catch (UnsupportedJwtException e){
            log.error(e.getMessage() + AllMessageConstant.JwtUnsupportedMessage);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage() + AllMessageConstant.JwtIllegalArgumentMessage);
        }
        return false;
    }
}
