package net.liuchenfei.ssitest.ctl;

import net.liuchenfei.ssitest.entitys.File;
import net.liuchenfei.ssitest.entitys.UserFile;
import net.liuchenfei.ssitest.services.FileService;
import net.liuchenfei.ssitest.services.SaveService;
import net.liuchenfei.ssitest.utils.Result;
import net.liuchenfei.ssitest.vos.FilesAndDirectoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liuchenfei on 2017/10/27.
 */
@Controller
@RequestMapping("/file")
public class FileCtrl {

    @Autowired
    private FileService fileService;
    @Autowired
    private SaveService saveService;

    @RequestMapping(value = "/getFilesAndDirectoriesByDirectoryId.do", method = RequestMethod.POST)
    @ResponseBody
    public Result<FilesAndDirectoriesVO> getFilesAndDirectoriesByDirectoryId(@RequestParam(value = "DirectoryId") String directoryId) {
        Result<FilesAndDirectoriesVO> result = new Result<>();
        try {
            result.setValue(fileService.getFilesAndDirectoriesByDirectoryId(directoryId));
            result.setSuccess(true);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/addDirectory.do", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addDirectory(@RequestParam String dirName, @RequestParam String parentDirId, @RequestParam String userId) {
        Result<String> result = new Result<>();
        try {
            result.setValue("新增目录成功");
            fileService.addDirectory(dirName, parentDirId, userId);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/addFile.do", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addDirectory(@RequestParam("file") MultipartFile file, @RequestParam String userId, @RequestParam String dirId) {
        Result<String> result = new Result<>();
        try {
            result.setValue("上传文件成功");
            fileService.addFile(file, userId, dirId);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/downloadFile.do", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(@RequestParam String userFileId, HttpServletResponse response) {
        try {
            UserFile uf = fileService.getUserFile(userFileId);
            File f = fileService.getFile(uf.getFileId());
            if (f.getFileStatus() != 0) {
                throw new Exception("文件被锁定，不能下载");
            }
            byte[] bytes = saveService.fetchFile(uf.getFileId());
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(uf.getFileName().getBytes("utf-8"), "ISO8859-1"));
            response.setContentType(f.getContentType());
            response.getOutputStream().write(bytes, 0, new Long(f.getFileSize()).intValue());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/getAllFiles.do", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<File>> getAllFiles() {
        Result<List<File>> files = new Result<>();
        try {
            files.setValue(fileService.getAllFiles());
        } catch (Exception e) {
            System.out.println(e);
            files.setError(e.getMessage());
        }
        return files;
    }

    @RequestMapping(value = "/adminDownloadFile.do", method = RequestMethod.GET)
    @ResponseBody
    public void adminDownloadFile(@RequestParam String fileId, HttpServletResponse response) {
        try {
            File f = fileService.getFile(fileId);
            byte[] bytes = saveService.fetchFile(fileId);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(f.getFileName().getBytes("utf-8"), "ISO8859-1"));
            response.setContentType(f.getContentType());
            response.getOutputStream().write(bytes, 0, new Long(f.getFileSize()).intValue());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/deleteDir.do", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteDir(@RequestParam String ids) {
        Result<String> result = new Result<>();
        try {
            result.setValue("删除成功");
            fileService.deleteDir(ids);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/deleteFile.do", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteFile(@RequestParam String ids) {
        Result<String> result = new Result<>();
        try {
            result.setValue("删除成功");
            fileService.deleteFile(ids);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/lockFile.do", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> lockFile(@RequestParam String ids) {
        Result<String> result = new Result<>();
        try {
            result.setValue("锁定成功");
            fileService.lockFile(ids);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

}
