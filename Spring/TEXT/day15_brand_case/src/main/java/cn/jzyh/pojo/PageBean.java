package cn.jzyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {

    /**
     * 查询后的分页数据
     */
    private List<Brand> rows;

    /**
     * 总记录数据
     */
    private Integer totalCount;
}
