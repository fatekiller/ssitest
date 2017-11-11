package net.liuchenfei.ssitest.services;

import net.liuchenfei.ssitest.entitys.File;
import net.liuchenfei.ssitest.entitys.UserFile;
import net.liuchenfei.ssitest.vos.FilesAndDirectoriesVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by liuchenfei on 2017/10/27.
 */
public interface FileService {
    FilesAndDirectoriesVO getFilesAndDirectoriesByDirectoryId(String directoryId);

    void addDirectory(String dirName, String parentDirId, String userId);

    String addFile(MultipartFile file, String userId, String dirId) throws Exception;

    void deleteDir(String dirId);

    File getFile(String fileId);

    void deleteFile(String ids);

    UserFile getUserFile(String userFileId);

    List<File> getAllFiles();

    void lockFile(String ids);
}
