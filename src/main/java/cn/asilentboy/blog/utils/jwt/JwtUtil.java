package cn.asilentboy.blog.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private static final Long JWT_TTL = 1000 * 60 * 60L;

    private static final String SECRET_KEY = "test_secret_key";

    private static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttl, String uuid) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttl == null)
            ttl = JWT_TTL;
        long expireMillis = nowMillis + ttl;
        Date expireDate = new Date(expireMillis);

        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("zblog")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(algorithm, secretKey);
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
