package cn.jzyh.pojo;

import lombok.Data;

import java.util.List;

//分页查询的javaBean

@Data

public class PageBean {
    //总记录数
    private Integer totalCount;

    //当前页数
    private List<Brand> rows;

}
