package net.liuchenfei.ssitest.vos;

import net.liuchenfei.ssitest.entitys.Directory;
import net.liuchenfei.ssitest.entitys.User;

import java.io.Serializable;

/**
 * Created by liuchenfei on 2017/5/11.
 */
public class UserVO implements Serializable{
    private String userName;
    private String password;
    private String role;
    private String userId;
    private Directory rootDir;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Directory getRootDir() {
        return rootDir;
    }

    public void setRootDir(Directory rootDir) {
        this.rootDir = rootDir;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public UserVO(User user) {
        userId = user.getUserId();
        userName = user.getUserName();
        password = user.getUserPass();
        role = user.getUserRole() == 0 ? "user" : "admin";
    }
}
