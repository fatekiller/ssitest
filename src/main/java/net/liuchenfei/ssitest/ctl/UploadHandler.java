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
            File file=new File("d:/images/"+new Random().nextInt()+".pdf");
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
}
