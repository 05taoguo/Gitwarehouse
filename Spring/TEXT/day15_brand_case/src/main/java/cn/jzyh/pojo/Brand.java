package cn.jzyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    // id 主键
    private Integer id;
    // 品牌名称
    private String brandName;
    // 企业名称
    private String companyName;
    // 排序字段
    private Integer ordered;
    // 描述信息
    private String description;
    // 状态：0：禁用  1：启用
    private Integer status;

    //状态描述 :0：禁用  1：启用
    private String statusDesc;

    public String getStatusDesc() {
        if(getStatus() == null){
            return "未知";
        }
        return getStatus() == 1 ? "开启" : "禁止";
    }
}
