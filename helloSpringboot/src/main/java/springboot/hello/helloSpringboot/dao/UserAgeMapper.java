package springboot.hello.helloSpringboot.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import springboot.hello.helloSpringboot.entity.User;


@Repository
public interface UserAgeMapper extends BaseMapper<User> {
    User helloSpringDao();
}
