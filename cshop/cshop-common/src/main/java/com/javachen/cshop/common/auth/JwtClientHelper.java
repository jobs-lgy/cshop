package com.javachen.cshop.common.auth;

import com.javachen.cshop.common.util.ObjectUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@Component
@Data
@ConfigurationProperties(prefix = "cshop.jwt")
public class JwtClientHelper {
    /**
     * 公钥地址
     */
    private String publicKeyPath;

    /**
     * cookie名字
     */
    private String cookieName = JwtConstants.COOKIE_NAME;

    /**
     * 公钥
     */
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        try {
            this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    ;

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    private Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     * @return
     * @throws Exception
     */
    private Jws<Claims> parserToken(String token, byte[] publicKey) {
        return parserToken(token, RsaUtils.getPublicKey(publicKey));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public AuthUser getAuthUserFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new AuthUser(
                ObjectUtils.toLong(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public AuthUser getAuthUserFromToken(String token, byte[] publicKey) {
        return getAuthUserFromToken(token, RsaUtils.getPublicKey(publicKey));
    }

    public AuthUser getAuthUserFromToken(String token) {
        return getAuthUserFromToken(token, publicKey);
    }
}