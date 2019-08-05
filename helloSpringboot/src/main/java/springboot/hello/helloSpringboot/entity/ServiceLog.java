package springboot.hello.helloSpringboot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName("service_log")
public class ServiceLog {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    @TableField("address")
    private String address;
    @TableField("begin_time")
    private Date beginTime;
    @TableField("end_time")
    private Date endTime;
    @TableField("message")
    private String message;
    @TableField("ip")
    private String ip;
    @TableField("error")
    private String error;
}
