package com.javachen.cshop.common.auth;

import com.javachen.cshop.common.utils.ObjectUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Configuration
@ConfigurationProperties(prefix = "cshop.jwt")
@ConditionalOnProperty("cshop.jwt.publicKeyPath") //初始化条件
@Slf4j
public class JwtHelper {
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
    private int expireMinutes = JwtConstants.EXPIRE_MINUTES;

    /**
     * cookie名字
     */
    private String cookieName = JwtConstants.COOKIE_NAME;

    /**
     * cookie生命周期
     */
    private Integer cookieMaxAge = JwtConstants.COOKIE_MAX_AGE;


    /**
     * 公钥
     */
    private PublicKey publicKey;
    /**
     * 私钥
     */
    private PrivateKey privateKey;


    @PostConstruct
    public void init() {
        try {
            //这是为了初始化公钥和秘钥，只在初始化时候执行一次
            if(!StringUtils.isEmpty(publicKeyPath) && !StringUtils.isEmpty(privateKeyPath)){
                File pubKey = new File(publicKeyPath);
                File priKey = new File(privateKeyPath);
                if (!pubKey.exists() || !priKey.exists()) {
                    // 生成公钥和私钥
                    RsaUtils.generateKey(publicKeyPath, privateKeyPath, secret);
                }
            }

            // 获取公钥和私钥
            if(!StringUtils.isEmpty(publicKeyPath)){
                this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
            }

            if(!StringUtils.isEmpty(privateKeyPath)){
                this.privateKey = RsaUtils.getPrivateKey(privateKeyPath);
            }
        } catch (Exception e) {
            log.error("初始化jwt失败：{}",e.toString());
            throw new RuntimeException();
        }
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    private Jws<Claims> parserToken(String token, PublicKey publicKey) {
        if(publicKey==null){
            throw new AuthFailedException("解析token失败，公钥为空");
        }
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
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
        return getAuthUserFromToken(token, this.getPublicKey());
    }

    /**
     * 私钥加密token
     *
     * @param authUser 载荷中的数据
     * @return
     * @throws Exception
     */
    public String generateToken(AuthUser authUser,PrivateKey privateKey) {
        if(this.getPrivateKey()==null){
            throw new AuthFailedException("生成私钥失败");
        }

        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, authUser.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, authUser.getUsername())
                .setExpiration(DateTime.now().plusDays(this.getExpireMinutes()).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param authUser   载荷中的数据
     * @param privateKey 私钥字节数组
     * @return
     * @throws Exception
     */
    public String generateToken(AuthUser authUser, byte[] privateKey) {
        return generateToken(authUser,RsaUtils.getPrivateKey(privateKey));
    }

    public String generateToken(AuthUser authUser) {
        return generateToken(authUser,privateKey);
    }
}