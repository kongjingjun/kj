package springboot.hello.helloSpringboot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("user_age")
@Data
public class UserAge {
    @TableId("id")
    private int id;
    @TableField("name")
    private String name;
}
