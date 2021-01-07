package me.zhengjie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
