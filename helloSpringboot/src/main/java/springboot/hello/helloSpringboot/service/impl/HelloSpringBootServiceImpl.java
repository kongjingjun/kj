package springboot.hello.helloSpringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.hello.helloSpringboot.dao.UserMapper;
import springboot.hello.helloSpringboot.entity.User;
import springboot.hello.helloSpringboot.service.HelloSpringBootService;

@Service
public class HelloSpringBootServiceImpl implements HelloSpringBootService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public User helloSpring(User user) {
        userMapper.selectById(1);
        User user1 = new User();
        user1.setId(4);
        user1.setName("23113");
        //userMapper.insert(user1);
        //userMapper.insert(user);
        return userMapper.helloSpringDao();
    }
}
