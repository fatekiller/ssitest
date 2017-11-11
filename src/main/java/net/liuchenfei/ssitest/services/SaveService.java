package net.liuchenfei.ssitest.services;

import java.io.File;

/**
 * Created by liuchenfei on 2017/11/10.
 */
public interface SaveService {
    void saveFile(File tempFile, String fileId) throws Exception;

    byte[] fetchFile(String fileId) throws Exception;

}
