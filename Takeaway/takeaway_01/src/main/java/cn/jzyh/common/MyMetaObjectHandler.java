package cn.jzyh.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /*
    * 插入自动填充*/
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段，自动填充，insert");
        // 发布时间
        metaObject.setValue("createTime", LocalDateTime.now());
        // 更新时间
        metaObject.setValue("updateTime",LocalDateTime.now());
        // 发布人
        metaObject.setValue("createUser",new Long(1));
        // 更新人
        metaObject.setValue("updateUser",new Long(1));
    }

    /*
    * 修改自动填充*/
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段，自动填充,update");

        long id = Thread.currentThread().getId();
        log.info("线程id：{}",id);
        //修改时间
        metaObject.setValue("updateTime",LocalDateTime.now());
        //修改人
        metaObject.setValue("updateUser",new Long(1));
    }
}
