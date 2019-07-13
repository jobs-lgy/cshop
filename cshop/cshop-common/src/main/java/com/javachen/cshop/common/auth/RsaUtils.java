package com.javachen.cshop.common.auth;

import com.javachen.cshop.common.exception.BusinessException;
import com.javachen.cshop.common.exception.ErrorCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtils {
    /**
     * 从文件中读取公钥
     *
     * @param filename 公钥保存路径，相对于classpath
     * @return 公钥对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String filename)  {
        byte[] bytes = readFile(filename);
        return getPublicKey(bytes);
    }

    /**
     * 从文件中读取密钥
     *
     * @param filename 私钥保存路径，相对于classpath
     * @return 私钥对象
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String filename) {
        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);
    }

    /**
     * 获取公钥
     *
     * @param bytes 公钥的字节形式
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] bytes)  {
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(spec);
        } catch (NoSuchAlgorithmException e) {
            throw new AuthFailedException();
        } catch (InvalidKeySpecException e) {
            throw new AuthFailedException();
        }
    }

    /**
     * 获取密钥
     *
     * @param bytes 私钥的字节形式
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] bytes)  {
        try {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(spec);
        } catch (NoSuchAlgorithmException e) {
            throw  new AuthFailedException();
        } catch (InvalidKeySpecException e) {
            throw new AuthFailedException();
        }
    }

    public static void generateKey(String publicKeyFilename,String privateKeyFilename, String secret)  {
        KeyPair keyPair = generateKey(secret);
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        writeFile(publicKeyFilename, publicKeyBytes);

        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        writeFile(privateKeyFilename, privateKeyBytes);
    }

    private static KeyPair generateKey(String secret)  {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(ErrorCode.AUTHORIZED_FAIL);
        }
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair;
    }


    private static byte[] readFile(String fileName) {
        try {
            return Files.readAllBytes(new File(fileName).toPath());
        } catch (IOException e) {
            throw  new AuthFailedException();
        }
    }

    private static void writeFile(String destPath, byte[] bytes) {
        try {
            File dest = new File(destPath);
            if (!dest.exists()) {
                dest.createNewFile();
            }
            Files.write(dest.toPath(), bytes);
        } catch (IOException e) {
            throw new AuthFailedException();
        }
    }
}