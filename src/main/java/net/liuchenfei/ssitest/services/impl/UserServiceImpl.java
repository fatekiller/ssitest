package net.liuchenfei.ssitest.services.impl;

import net.liuchenfei.ssitest.daos.DirectoryMapper;
import net.liuchenfei.ssitest.daos.UserMapperEx;
import net.liuchenfei.ssitest.entitys.Directory;
import net.liuchenfei.ssitest.entitys.DirectoryExample;
import net.liuchenfei.ssitest.entitys.User;
import net.liuchenfei.ssitest.services.UserService;
import net.liuchenfei.ssitest.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuchenfei on 2017/5/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapperEx mapper;
    @Autowired
    private DirectoryMapper directoryMapper;

    @Override
    @Transactional
    public UserVO login(String username, String password) {
        User user = mapper.selectUserByUserNameAndPass(username, password);
        UserVO vo=new UserVO(user);
        DirectoryExample example = new DirectoryExample();
        DirectoryExample.Criteria criteria = example.createCriteria();
        criteria.andDirectoryNameEqualTo("/");
        List<Directory> result = directoryMapper.selectByExample(example);
        vo.setRootDir(result.get(0));
        return vo;
    }

    @Override
    @Transactional
    public UserVO register(String username, String password) throws Exception {
        if (mapper.selectUserByUserName(username) != null) {
            throw new Exception("该用户名已存在");
        }
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, username, password, 0, 0);
        Directory directory = new Directory(UUID.randomUUID().toString(), userId, "/", null, new Date());
        mapper.insert(user);
        directoryMapper.insert(directory);
        return new UserVO(user);
    }
}
