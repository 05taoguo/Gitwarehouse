package cn.jzyh.dao;

import cn.jzyh.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//数据库交互接口
@Mapper
public interface BookDao {

    @Select("select * from tbl_book where id = #{id}")
    Book getId(Integer id);
}
