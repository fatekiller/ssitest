package net.liuchenfei.ssitest.services;

import net.liuchenfei.ssitest.vos.UserVO;

/**
 * Created by liuchenfei on 2017/5/11.
 */
public interface UserService {
    UserVO login(String username,String passwordS);
}
