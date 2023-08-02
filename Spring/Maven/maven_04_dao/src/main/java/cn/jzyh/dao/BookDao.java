package cn.jzyh.dao;

import cn.jzyh.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO 添加@Mapper
@Mapper
public interface BookDao {
    @Insert("insert into tbl_book(type,name,description) values(#{type},#{name},#{description})")
    int save(Book book);

    @Update("update tbl_book set type=#{type},name = #{name}, description = #{description} where id = #{id}")
    int update(Book book);

    @Delete("delete from tbl_book where id = #{id}")
    int delete(Integer id);

    @Select("select * from tbl_book where id = #{id}")
    Book getById(Integer id);

    @Select("select * from tbl_book ")
    List<Book> getAll();
}
