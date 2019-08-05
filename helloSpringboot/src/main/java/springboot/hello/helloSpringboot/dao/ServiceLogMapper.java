package springboot.hello.helloSpringboot.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import springboot.hello.helloSpringboot.entity.ServiceLog;

@Repository
public interface ServiceLogMapper extends BaseMapper<ServiceLog> {
}
