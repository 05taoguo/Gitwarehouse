package UserMapper1_0.mybatis;

import BeanClass.Bean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

//    List<Bean> one();
//
//
//    @Select("select username from table1 where username=&{username}")
    Bean SelectUser(@Param("username") String username,@Param("password") String password);

    int inertUser(@Param("username") String username,@Param("password") String password);

}