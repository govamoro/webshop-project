package com.radnoti.webshop.util;

import com.radnoti.webshop.model.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

//TODO ezek kozul lehet nehany/vagy az osszes fasz tudja static!!
@Component
public class JwtUtil {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateJwt(User user){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Integer userId = user.getId();
        String role = user.getRole().getType();
        String email = user.getEmail();
        String userName = user.getUserName();
        String fullName = user.getFirstName() + " " + user.getLastName();


        Instant now = Instant.now();

        String token = Jwts.builder()
                .setIssuer(issuer)
                .claim("id", userId)
                .claim("email", email )
                .claim("userName", userName)
                .claim("role", role)
                .claim("fullName", fullName)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .signWith(
                        signatureAlgorithm,
                        signingKey
                )
                .compact();
        return "Bearer " +token;

    }

    //bemeno param: bearer eylnfjkjds.qfjbsdkhjbc.djksbjckhd
    public boolean validateJwt(String authHeader) {
        if(authHeader.equals("null")){
            return false;
        }
        try{
            String cleanToken = authHeader.split(" ")[1];
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(cleanToken);

            String[] parts = cleanToken.split("\\.");
            JSONObject data = new JSONObject(decode(parts[1]));

            if (data.getLong("exp") > (System.currentTimeMillis() / 1000)) {
                return true;
            }
            return false;
        }catch (IllegalArgumentException | MalformedJwtException | NullPointerException |
                ExpiredJwtException e){
            return  false;
        }
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    public String getUserNameFromJwt(String jwt){
        String cleanToken = jwt.split(" ")[1];
        String[] parts = cleanToken.split("\\.");
        JSONObject data = new JSONObject(decode(parts[1]));
        return data.getString("userName");
    }

    public Integer getIdFromAuthHeader(String header) {
        String cleanToken = header.split(" ")[1];
        String[] parts = cleanToken.split("\\.");
        JSONObject data = new JSONObject(decode(parts[1]));
        return data.getInt("id");

    }
}
