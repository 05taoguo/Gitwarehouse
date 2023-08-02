package cn.jzyh.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class User {
    private String name;
    private int age;

    private Address address;
}
