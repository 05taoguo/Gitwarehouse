package cn.jzyh.dao;

import cn.jzyh.domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AccountDao {
    @Insert("insert into dept(dname,loc)values(#{dname},#{loc})")
    void save(Account account);

    @Delete("delete from dept where id = #{id} ")
    void delete(Integer id);

    @Update("update dept set dname = #{dname} , loc = #{loc} where id = #{id} ")
    void update(Account account);

    @Select("select * from dept")
    List<Account> findAll();

    @Select("select * from dept where id = #{id} ")
    Account findById(Integer id);
}
