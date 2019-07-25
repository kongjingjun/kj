package springboot.hello.helloSpringboot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


@TableName("user")
@Data
public class User implements Serializable {


    @TableId("id")
    private int id;
    @TableField("name")
    private String name;
}
