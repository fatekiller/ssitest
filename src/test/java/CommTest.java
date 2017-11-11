import net.liuchenfei.ssitest.utils.MD5Handler;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchenfei on 2017/9/24.
 */
public class CommTest {
    @Test
    public void testSendFile() {
        try {
            Socket s = new Socket("127.0.0.1", 9991);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            os.write("me.jpg".getBytes());
            byte[] in_byte = new byte[20480 * 8];
            is.read(in_byte);
            System.out.println(new String(in_byte));
            FileInputStream fin = new FileInputStream("E:\\me.jpg");
            byte[] file_byte = new byte[20480 * 8];
            fin.read(file_byte);
            os.write(file_byte);
            is.close();
            fin.close();
            os.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchFile() {
        try {
            String str = "a,b,c";
            String[] arr = str.split(",");
            String s = "";
            for (int i = 0; i < arr.length; i++) {
                arr[i] = "'" + arr[i] + "'";
                s += arr[i] + ",";
            }
            System.out.println(s.substring(0, s.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCombinFile() {
        try {
            int index = 0;
            FileInputStream fis = new FileInputStream("E:\\xxx.jpg");
            MD5Handler md5Handler = new MD5Handler();
            int size = fis.available();
            byte[] block = new byte[1024 * 1024];
            List<String> names = new ArrayList<>();
            while (fis.available() != 0) {
                String name = index++ + "";
                names.add(name);
                FileOutputStream fos = new FileOutputStream(name);
                int len = fis.read(block);
                md5Handler.updateDigest(block, len);
                fos.write(block);
                fos.close();
            }
            fis.close();
            System.out.println(md5Handler.getHashStr());
            FileOutputStream fos = new FileOutputStream("out.jpg");
            byte[] result = new byte[10 * 1024 * 1024];
            index = 0;
            for (String f : names) {
                byte[] bs = new byte[1024 * 1024];
                FileInputStream nfis = new FileInputStream(f);
                nfis.read(bs);
                nfis.close();
                for (byte b : bs) {
                    result[index++] = b;
                }
            }
            System.out.println(size);
            MD5Handler m2 = new MD5Handler();
            m2.updateDigest(result, size);
            System.out.println(m2.getHashStr());
            fos.write(result, 0, size);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testByte() {
        try {
            String s = "汉子长度测试.ppt";
            System.out.println(s.getBytes("utf-8").length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
