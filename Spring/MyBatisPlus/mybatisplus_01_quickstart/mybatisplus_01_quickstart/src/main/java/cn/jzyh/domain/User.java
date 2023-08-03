package cn.jzyh.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/*@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode*/

@Data
//表名匹配，会忽略大小写，防止tbl_user
@TableName("user")
public class User {
    private Long id;
    private String name;

    //    别名,设置不参与查询
    @TableField(value = "pwd", select = false)
    private String password;
    private Integer age;
    private String tel;

    //    数据库不存在
    @TableField(exist = false)
    private Integer online;
}
