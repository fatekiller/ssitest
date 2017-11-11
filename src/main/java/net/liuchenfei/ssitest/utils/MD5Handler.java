package net.liuchenfei.ssitest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liuchenfei on 2017/11/11.
 */
public class MD5Handler {
    private char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MessageDigest digest;

    public MD5Handler() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("MD5");
    }

    public void updateDigest(byte[] bytes, int len) {
        digest.update(bytes, 0, len);
    }

    public String getHashStr() {
        return getHexStr(digest.digest());
    }

    private String getHexStr(byte[] input) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            /**
             * 将一个byte分为两部分，前面是高四位，后面是低四位
             */
            sb.append(hexChar[(input[i] & 0xf0) >>> 4]);
            sb.append(hexChar[input[i] & 0x0f]);
        }
        return sb.toString();
    }
}
