package cn.jzyh.mapper;

import cn.jzyh.pojo.Bean;
import cn.jzyh.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //查询注册
    User SelectUser(@Param("username") String username,@Param("password") String password);

    //新建注册
    int inertUser(@Param("username") String username,@Param("password") String password);

    //据用户名和密码能查询用户
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User select(@Param("username") String username, @Param("password") String password);

    @Select("select * from tb_user where username=#{username}")
    User selectByParam(@Param("username") String username);

    void addUser(User users);

    @Select("select * from tb_brand")
    
    List<Bean> selectAll();

    void addBrand(Bean brand);

    @Select("select * from tb_brand where id = #{id}")
    
    Bean selectById(@Param("id") Integer id);

    void updateById(Bean brand);

    void deleteById(@Param("id") int id);
}