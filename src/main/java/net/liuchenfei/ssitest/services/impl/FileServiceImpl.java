package net.liuchenfei.ssitest.services.impl;

import net.liuchenfei.ssitest.daos.DirectoryMapper;
import net.liuchenfei.ssitest.daos.FileMapper;
import net.liuchenfei.ssitest.daos.UserFileMapper;
import net.liuchenfei.ssitest.entitys.*;
import net.liuchenfei.ssitest.services.FileService;
import net.liuchenfei.ssitest.services.SaveService;
import net.liuchenfei.ssitest.utils.MD5Handler;
import net.liuchenfei.ssitest.vos.FilesAndDirectoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuchenfei on 2017/10/27.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    DirectoryMapper directoryMapper;
    @Autowired
    UserFileMapper userFileMapper;
    @Autowired
    SaveService saveService;

    @Override
    public FilesAndDirectoriesVO getFilesAndDirectoriesByDirectoryId(String directoryId) {
        FilesAndDirectoriesVO vo = new FilesAndDirectoriesVO();
        Directory directory = directoryMapper.selectByPrimaryKey(directoryId);
        List<FilesAndDirectoriesVO.FileOrDirVO> result = new ArrayList<>();
        UserFileExample fileExample = new UserFileExample();
        fileExample.createCriteria().andDirIdEqualTo(directoryId);
        List<UserFile> fileList = userFileMapper.selectByExample(fileExample);
        DirectoryExample directoryExample = new DirectoryExample();
        DirectoryExample.Criteria dirC = directoryExample.createCriteria();
        dirC.andParentDirectoryIdEqualTo(directoryId);
        List<Directory> directories = directoryMapper.selectByExample(directoryExample);
        for (Directory dir : directories) {
            result.add(vo.new FileOrDirVO(dir));
        }
        for (UserFile file : fileList) {
            File f = fileMapper.selectByPrimaryKey(file.getFileId());
            result.add(vo.new FileOrDirVO(file, f));
        }
        vo.setDirectory(directory);
        vo.setFiles(result);
        return vo;
    }


    @Override
    public void addDirectory(String dirName, String parentDirId, String userId) {
        Directory directory = new Directory(UUID.randomUUID().toString(), userId, dirName, parentDirId, new Date());
        directoryMapper.insert(directory);
    }

    @Transactional
    @Override
    public String addFile(MultipartFile file, String userId, String dirId) throws Exception {
        UserFileExample userFileExample = new UserFileExample();
        userFileExample.createCriteria().andFileNameEqualTo(file.getOriginalFilename()).andDirIdEqualTo(dirId);
        List<UserFile> userFiles = userFileMapper.selectByExample(userFileExample);
        if (userFiles.size() > 0) {
            throw new Exception("同一文件夹下不能有相同名称的文件");
        }
        String checkCode = getCheckCode(file.getInputStream());
        java.io.File tempf = new java.io.File("temp");
        FileExample fileExample = new FileExample();
        FileExample.Criteria criteria = fileExample.createCriteria();
        criteria.andCheckCodeEqualTo(checkCode);
        List<File> files = fileMapper.selectByExample(fileExample);
        String fileId = "";
        if (files.size() > 0) {
            fileId = files.get(0).getFileId();
            tempf.delete();
        } else {
            fileId = UUID.randomUUID().toString();
            saveService.saveFile(tempf, fileId);
            File f = new File(fileId, file.getOriginalFilename(), file.getSize(), userId, new Date(), getFileType(file.getOriginalFilename()), 0, file.getContentType(), checkCode);
            fileMapper.insert(f);
        }
        UserFile userFile = new UserFile(UUID.randomUUID().toString(), userId, fileId, new Date(), dirId, 0, file.getOriginalFilename());
        userFileMapper.insert(userFile);
        return fileId;
    }

    @Override
    public void deleteDir(String dirIds) {
        String[] arr = dirIds.split(",");
        for (String id : arr) {
            directoryMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public File getFile(String fileId) {
        return fileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public void deleteFile(String ids) {
        String[] arr = ids.split(",");
        for (String id : arr) {
            userFileMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public UserFile getUserFile(String userFileId) {
        return userFileMapper.selectByPrimaryKey(userFileId);
    }

    @Override
    public List<File> getAllFiles() {
        FileExample example = new FileExample();
        return fileMapper.selectByExample(example);
    }

    @Override
    public void lockFile(String ids) {
        String[] arr = ids.split(",");
        for (String id : arr) {
            File f = fileMapper.selectByPrimaryKey(id);
            f.setFileStatus(1);
            fileMapper.updateByPrimaryKey(f);
        }
    }

    private Integer getFileType(String fileName) {
        String[] docs = {"doc", "docx", "pdf", "xls", "txt", "xlsx", "ppt"};
        String[] imgs = {"png", "jpg", "jpeg", "gif", "bmp"};
        String[] music = {"mp3", "wav", "wma"};
        String[] video = {"mp4", "3gp", "avi"};
        String suffix = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
        for (String s : docs) {
            if (s.equals(suffix)) return 1;
        }
        for (String s : imgs) {
            if (s.equals(suffix)) return 2;
        }
        for (String s : music) {
            if (s.equals(suffix)) return 3;
        }
        for (String s : video) {
            if (s.equals(suffix)) return 4;
        }
        return 5;
    }

    /**
     * 建立临时文件，同时读取md5码
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    private String getCheckCode(InputStream inputStream) throws Exception {
        FileOutputStream fos = new FileOutputStream("temp");
        byte[] buffer = new byte[1024 * 64];
        int numRead = 0;
        MD5Handler m = new MD5Handler();
        while ((numRead = inputStream.read(buffer)) != -1) {
            m.updateDigest(buffer, numRead);
            fos.write(buffer);
        }
        inputStream.close();
        fos.close();
        return m.getHashStr();
    }

}
