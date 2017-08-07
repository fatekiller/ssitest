package net.liuchenfei.ssitest.vos;

import net.liuchenfei.ssitest.entitys.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by liuchenfei on 2017/5/11.
 */
public class UserVO implements Serializable{
    private String userName;
    private String password;
    private String regDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public UserVO(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.regDate = new SimpleDateFormat("yyyy-MM-dd").format(user.getRegdate());
    }
}
