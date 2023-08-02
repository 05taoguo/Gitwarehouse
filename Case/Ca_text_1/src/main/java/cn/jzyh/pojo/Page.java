package cn.jzyh.pojo;

import lombok.Data;


import java.util.List;


@Data
public class Page {
//    总页数
    private Integer totalCount;

//    当前页
    private List<Brand> row;
}
