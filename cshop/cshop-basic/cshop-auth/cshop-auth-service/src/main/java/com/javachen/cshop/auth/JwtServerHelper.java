package com.javachen.cshop.auth;

import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.auth.JwtConstants;
import com.javachen.cshop.common.auth.RsaUtils;
import com.javachen.cshop.common.util.ObjectUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
@Data
@ConfigurationProperties(prefix = "cshop.jwt")
public class JwtServerHelper {
    /**
     * 密钥
     */
    private String secret;

    /**
     * 公钥地址
     */
    private String publicKeyPath;

    /**
     * 私钥地址
     */
    private String privateKeyPath;

    /**
     * token过期时间
     */
    private int expireMinutes=JwtConstants.EXPIRE_MINUTES;

    /**
     * 公钥
     */
    private PublicKey publicKey;

    /**
     * 私钥
     */
    private PrivateKey privateKey;

    /**
     * cookie名字
     */
    private String cookieName= JwtConstants.COOKIE_NAME;

    /**
     * cookie生命周期
     */
    private Integer cookieMaxAge=JwtConstants.COOKIE_MAX_AGE;


    @PostConstruct
    public void init() {
        try {
            File pubKey = new File(publicKeyPath);
            File priKey = new File(privateKeyPath);
            if (!pubKey.exists() || !priKey.exists()) {
                // 生成公钥和私钥
                RsaUtils.generateKey(publicKeyPath, privateKeyPath, secret);
            }
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 私钥加密token
     *
     * @param authUser      载荷中的数据
     * @return
     * @throws Exception
     */
    public String generateToken(AuthUser authUser)  {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, authUser.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, authUser.getUsername())
                .setExpiration(DateTime.now().plusDays(this.getExpireMinutes()).toDate())
                .signWith(SignatureAlgorithm.RS256, this.getPrivateKey())
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param authUser      载荷中的数据
     * @param privateKey    私钥字节数组
     * @return
     * @throws Exception
     */
    public String generateToken(AuthUser authUser, byte[] privateKey)  {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, authUser.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, authUser.getUsername())
                .setExpiration(DateTime.now().plusMinutes(this.getExpireMinutes()).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    public AuthUser getAuthUserFromToken(String token)  {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.getPublicKey()).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return new AuthUser(
                ObjectUtils.toLong(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }
}