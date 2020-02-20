package com.lifp.mpdemo.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus 自带的填充功能实现
 * 例如自动填充update_time以及create_time
 *
 * @author lifp
 * @Date 2019/12/26
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 在mp执行添加操作的时候执行
     *
     * @Author lifp
     * @Date 2019/12/26
     * @param: metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("version", 1, metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
    }

    /**
     * 在mp执行更新操作的时候执行
     *
     * @Author lifp
     * @Date 2019/12/26
     * @param: metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
