package net.liuchenfei.ssitest.ctl;


import com.google.gson.Gson;
import net.liuchenfei.ssitest.services.UserService;
import net.liuchenfei.ssitest.utils.GsonU;
import net.liuchenfei.ssitest.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login.do")
    @ResponseBody
    public UserVO login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password") String password){
        UserVO user=userService.login(username,password);
        return user;
    }

}
