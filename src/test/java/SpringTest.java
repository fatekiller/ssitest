import net.liuchenfei.ssitest.daos.UserMapper;
import net.liuchenfei.ssitest.entitys.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuchenfei on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:datasource-redis.xml"})
public class SpringTest {
    @Autowired
    UserMapper mapper;
    @Autowired
    RedisTemplate redisTemplate;
    
    @Test
    public void testInsert() {
        try {
            User u=new User();
//            u.setUserid(UUID.randomUUID().toString());
//            u.setUsername("username");
//            u.setPassword("password");
//            u.setRegdate(new Date());
            mapper.insertSelective(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRedis() {
        try {
//            User u=new User();
//            u.setUserid(UUID.randomUUID().toString());
//            u.setUsername("username");
//            u.setPassword("password");
//            u.setRegdate(new Date());
//            //redisTemplate.restore("testkey", "testvalue".getBytes(),1000L, TimeUnit.SECONDS);
//            redisTemplate.opsForValue().set("testkey","testvalue");
//
//            String a=redisTemplate.opsForValue().get("testkey").toString();
//            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
