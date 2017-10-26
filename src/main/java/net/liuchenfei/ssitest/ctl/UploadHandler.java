package net.liuchenfei.ssitest.ctl;

import net.liuchenfei.ssitest.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by liuchenfei on 2017/8/6.
 */
@Controller
@RequestMapping("/upload")
public class UploadHandler {

    @RequestMapping(value = "/upload.do",method = RequestMethod.POST)
    @ResponseBody
    private Result<String> upload(HttpServletRequest request){
        Result<String> result=new Result<>();
        result.setValue("sd");
        result.setSuccess(true);
        result.setError("");
        try{
            File file=new File("d:/images/"+new Random().nextInt()+".png");
            FileOutputStream fos=new FileOutputStream(file);
            int a=0;
            while ((a=request.getInputStream().read())!=-1){
                fos.write(a);
            }
            request.getInputStream().close();
            fos.close();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return result;
    }

    @RequestMapping(value = "/upload_mul.do",method = RequestMethod.POST)
    @ResponseBody
    private Result<String> upload_mul(HttpServletRequest request){
        Result<String> result=new Result<>();
        try{
            if (!ServletFileUpload.isMultipartContent(request)) {
                result.setError("1000");
                return result;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("image");
            // 获得文件名：
            String fileName = file.getOriginalFilename();
            result.setValue("sd");
            result.setSuccess(true);
            result.setError("");
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return result;
    }
}
