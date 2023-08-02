package cn.jzyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


//品牌实现类
public class Brand {
    private Integer id;
    private String brandName;
    private String companyName;
    private Integer ordered;
    private String description;
    private Integer status;

    private String statusDesc;

    public String getStatusDesc() {
        if(getStatus() == null){
            return "未知";
        }
        return getStatus() == 1 ? "开启" : "禁止";
    }
}
