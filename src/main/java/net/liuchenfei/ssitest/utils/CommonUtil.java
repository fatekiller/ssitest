package net.liuchenfei.ssitest.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuchenfei on 2017/11/9.
 */
public class CommonUtil {

    public static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String formateDate(Date d) {
        SimpleDateFormat smf = new SimpleDateFormat("YY-MM-dd HH-mm");
        return smf.format(d);
    }

    public static String getMD5Hash(InputStream is) throws Exception {
        byte[] buffer = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        int numRead;
        while ((numRead = is.read(buffer)) != -1) {
            md5.update(buffer, 0, numRead);
        }
        is.close();
        byte[] digest = md5.digest();
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (int i = 0; i < digest.length; i++) {
            /**
             * 将一个byte分为两部分，前面是高四位，后面是低四位
             */
            sb.append(hexChar[(digest[i] & 0xf0) >>> 4]);
            sb.append(hexChar[digest[i] & 0x0f]);
        }
        return sb.toString();
    }
}
