package cn.jzyh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/*
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
*/


/*
AUTo(@): 使用数据库id自增策略控制id生成
NONE(1):不设置id生成策略
INPUT(2):用户手工输入id
ASSIGN ID(3):雪花算法生成id (可兼容数值型与字符串型)
ASSIGN UUID(4): 以UUID生成算法作为id生成策略
*/

@Data
//表名匹配，会忽略大小写，防止tbl_user
//@TableName("user")
public class User {

    //雪花算法
//    @TableId(type = IdType.ASSIGN_ID)
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
