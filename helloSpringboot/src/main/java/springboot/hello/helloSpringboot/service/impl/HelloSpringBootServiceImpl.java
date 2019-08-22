package springboot.hello.helloSpringboot.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.hello.helloSpringboot.dao.UserMapper;
import springboot.hello.helloSpringboot.entity.User;
import springboot.hello.helloSpringboot.service.HelloSpringBootService;

import java.nio.channels.WritableByteChannel;
import java.sql.Wrapper;

@Service
public class HelloSpringBootServiceImpl implements HelloSpringBootService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public User helloSpring(User user) {
        stringRedisTemplate.opsForValue().set("user","5");
        userMapper.selectById(1);
        User user1 = new User();
        user1.setId(4);
        user1.setName("23113");
        userMapper.insert(user1);
        userMapper.insert(user);
        EntityWrapper<User> taskWrapper = new EntityWrapper<>();
        taskWrapper.eq("name","23113");
        userMapper.selectList(taskWrapper);
        return userMapper.helloSpringDao();
    }
}
