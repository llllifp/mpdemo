package com.lifp.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author lifp
 * @TableField 自动添加注解
 * @Version 乐观锁注解
 * @TableLogic 逻辑删除注解
 * @Date 2019/12/26
 */
@Data
public class User {


    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 乐观锁的版本号
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
