package cn.jzyh.domain;


import lombok.Data;

import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Account implements Serializable {
    private int id;
    private String dname;
    private String loc;
}
