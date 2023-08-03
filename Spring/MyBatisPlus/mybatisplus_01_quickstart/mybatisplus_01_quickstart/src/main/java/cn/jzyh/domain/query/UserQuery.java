package cn.jzyh.domain.query;

import cn.jzyh.domain.User;
import lombok.Data;

//设置下限
@Data
public class UserQuery extends User {
    private Integer age2;
}
