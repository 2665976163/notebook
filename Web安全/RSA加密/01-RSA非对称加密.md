## RSA 非对称加密

> java 代码实现

```java
/**
 * 用于封装随机产生的公钥与私钥
 */
private static Map<String, String> keyMap = new HashMap<>();

public static void main(String[] args) throws Exception {
    /**
	 * 生成公钥与私钥
	 */
    genKeyPair();

    String message = "message context";
    System.out.println("随机生成的公钥为:" + keyMap.get("public"));
    System.out.println("随机生成的私钥为:" + keyMap.get("private"));
    
    String messageEn = encrypt(message, keyMap.get("public"));
    System.out.println(message + "\t加密后的字符串为:" + messageEn);
    String messageDe = decrypt(messageEn, keyMap.get("private"));
    System.out.println("还原后的字符串为:" + messageDe);
}

/**
  * 随机生成密钥对
  *
  * @throws NoSuchAlgorithmException
  */
public static void genKeyPair() throws NoSuchAlgorithmException {
    // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    // 初始化密钥对生成器，密钥大小为96-1024位
    keyPairGen.initialize(2048, new SecureRandom());
    // 生成一个密钥对，保存在keyPair中
    KeyPair keyPair = keyPairGen.generateKeyPair();
    // 得到私钥
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    // 得到公钥
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
    // 得到私钥字符串
    String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
    // 将公钥和私钥保存到Map
    keyMap.put("public", publicKeyString);
    keyMap.put("private", privateKeyString);
}

/**
  * RSA公钥加密
  *
  * @param str       加密字符串
  * @param publicKey 公钥
  * @return 密文
  * @throws Exception 加密过程中的异常信息
  */
public static String encrypt(String str, String publicKey) throws Exception {
    try {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
    } catch (Exception e) {
        return null;
    }
}

/**
  * RSA私钥解密
  *
  * @param str        加密字符串
  * @param privateKey 私钥
  * @return 铭文
  * @throws Exception 解密过程中的异常信息
  */
public static String decrypt(String str, String privateKey) {
    try {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    } catch (Exception e) {
        return null;
    }
}
```

