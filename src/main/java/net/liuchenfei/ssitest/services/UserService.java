package net.liuchenfei.ssitest.services;

import net.liuchenfei.ssitest.vos.UserVO;
import org.springframework.stereotype.Service;

/**
 * Created by liuchenfei on 2017/5/11.
 */
public interface UserService {
    UserVO login(String username,String password);

    UserVO register(String username,String password) throws Exception;
}
