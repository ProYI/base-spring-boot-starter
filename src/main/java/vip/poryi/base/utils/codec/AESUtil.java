package vip.poryi.base.utils.codec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * 微信小程序登录AES加解密工具
 */
public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/CBC/PKCS7Padding";

    /**
     * AES解密
     *
     * @param str
     * @return str
     * @throws Exception e
     */
    public static String decryptData(String str, String sessionKey, String iv) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        // 解密秘钥
        SecretKeySpec keySpec = null;
        try {
            keySpec = new SecretKeySpec(sessionKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 偏移量
        AlgorithmParameters parameters = AlgorithmParameters.getInstance(ALGORITHM);
        parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

        cipher.init(Cipher.DECRYPT_MODE, keySpec, parameters);
        return new String(cipher.doFinal(Base64Utils.decodeFromString(str)), StandardCharsets.UTF_8);
    }

    /**
     * AES加密
     *
     * @param str
     * @return str
     * @throws Exception e
     */
    public static String encryptData(String str, String sessionKey, String iv) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        // 加密秘钥
        SecretKeySpec keySpec = null;
        try {
            keySpec = new SecretKeySpec(sessionKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 偏移量
        AlgorithmParameters parameters = AlgorithmParameters.getInstance(ALGORITHM);
        parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameters);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }
}