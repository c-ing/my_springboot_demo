package com.example.my_springboot_demo.jwt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: cdc
 * @Date: 2020/1/3 10:34
 */
public class RsaKeyHelper {

    public RsaKeyHelper() {
    }

    //将公钥导入并返回按照 X.509 标准进行编码的密钥的字节
    public PublicKey getPublicKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    //导入密钥并生成使用PKCS#8标准作为密钥规范管理的编码格式私钥
    public PrivateKey getPrivateKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
    //传入公钥并返回按照 X.509 标准进行编码的公钥的字节
    public PublicKey getPublicKey(byte[] publicKey) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
    //传入密钥返回使用PKCS#8标准作为密钥规范管理的编码格式密钥
    public PrivateKey getPrivateKey(byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    //生成秘钥对，程序启动时可以运行以下方法，将生成的秘钥对放在静态变量里面存放，方便后续使用
    public static Map<String, byte[]> generateKey(String password) throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        //根据随机种子生成秘钥对
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        Map<String, byte[]> map = new HashMap();
        map.put("pub", publicKeyBytes);
        map.put("pri", privateKeyBytes);
        return map;
    }

    public static String toHexString(byte[] b) {
        return (new BASE64Encoder()).encodeBuffer(b);
    }

    public static final byte[] toBytes(String s) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(s);
    }


    public static void main(String[] args) throws Exception{

        Map<String,byte[]> map  = RsaKeyHelper.generateKey("password");
     //   System.out.println(RsaKeyHelper.toHexString(map.get("pri")));
        System.out.println(UUID.randomUUID());
    }
}
