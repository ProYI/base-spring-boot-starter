package vip.poryi.base.utils.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class AESUtilTest {
    @Test
    public void testAESUtil() throws Exception {
        String str = "这是一个测试字符串";
        String sessionKey = "proyiproyiproyii";
        String iv = "5e8y6w45ju8w9jq8";

        String encryptStr = AESUtil.encryptData(str, sessionKey, iv);
        log.info("加密后字符串: {}", encryptStr);
        String decryptStr = AESUtil.decryptData(encryptStr, sessionKey, iv);
        log.info("解密后字符串: {}", decryptStr);
    }
}