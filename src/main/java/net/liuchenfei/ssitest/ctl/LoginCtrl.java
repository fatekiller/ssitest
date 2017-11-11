package net.liuchenfei.ssitest.ctl;


import com.google.gson.Gson;
import net.liuchenfei.ssitest.services.UserService;
import net.liuchenfei.ssitest.utils.GsonU;
import net.liuchenfei.ssitest.utils.Result;
import net.liuchenfei.ssitest.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuchenfei on 2017/5/11.
 */
@Controller
@RequestMapping("/user")
public class LoginCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<UserVO> login(@RequestParam(value = "username")String username,
                               @RequestParam(value = "password") String password){
        Result<UserVO> result=new Result<>();
        try{
            result.setValue(userService.login(username,password));
            result.setSuccess(true);
        }catch (Exception e){
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<UserVO> register(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password") String password){
        Result<UserVO> result=new Result<>();
        try{
            result.setValue(userService.register(username,password));
            result.setSuccess(true);
        }catch (Exception e){
            result.setError(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

}
