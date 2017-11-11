package net.liuchenfei.ssitest.handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by liuchenfei on 2017/11/10.
 */
public class FileHandler {
    private Integer savePort = 9991;
    private Integer fetchPort = 9992;


    public void saveFile(String ip, String fileName, byte[] file_byte) throws Exception {
        Socket s = new Socket(ip, savePort);
        byte[] in_byte = new byte[20480 * 8];
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        os.write(fileName.getBytes());
        is.read(in_byte);
        System.out.println(new String(in_byte));
        os.write(file_byte);
        is.close();
        os.close();
        s.close();
    }

    public byte[] getFile(String ip, String fileName) throws Exception {
        Socket s = new Socket(ip, fetchPort);
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        os.write(fileName.getBytes());
        byte[] in_byte = new byte[1024 * 1024];
        int data;
        int index = 0;
        while ((data = is.read()) != -1) {
            in_byte[index++] = (byte) data;
        }
        is.close();
        os.close();
        s.close();
        return in_byte;
    }
}
