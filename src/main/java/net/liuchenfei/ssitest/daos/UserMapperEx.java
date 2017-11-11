package net.liuchenfei.ssitest.daos;

import net.liuchenfei.ssitest.entitys.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by liuchenfei on 2017/11/9.
 */
public interface UserMapperEx extends UserMapper {
    @Select("select user_id userId,user_name userName,user_pass userPass,user_role userRole,user_status userStatus from user where user_name=#{userName} and user_pass=#{password}")
    User selectUserByUserNameAndPass(@Param("userName") String userName, @Param("password") String password);

    @Select("select user_id userId,user_name userName,user_pass userPass,user_role userRole,user_status userStatus from user where user_name=#{userName}")
    User selectUserByUserName(@Param("userName") String userName);
}
