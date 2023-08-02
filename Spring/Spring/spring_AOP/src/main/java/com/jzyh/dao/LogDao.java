package com.jzyh.dao;

import org.apache.ibatis.annotations.Insert;

public interface LogDao {
    @Insert("insert into tbl_log(info,createData) values(#{info},now())")
    void log(String info);
}
