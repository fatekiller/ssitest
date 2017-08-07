package net.liuchenfei.ssitest.services.impl;

import net.liuchenfei.ssitest.daos.UserMapper;
import net.liuchenfei.ssitest.entitys.User;
import net.liuchenfei.ssitest.services.UserService;
import net.liuchenfei.ssitest.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by liuchenfei on 2017/5/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public UserVO login(String username, String password) {
        User user=mapper.selectUserByPwdAndUserName(username,password);
        UserVO vo=new UserVO(user);
        redisTemplate.persist(vo);
        return vo;
    }
}
