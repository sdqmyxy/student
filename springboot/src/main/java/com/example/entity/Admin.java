package com.example.entity;


import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class Admin {
    //ID
    private Integer id;
    //账号
    private String username;
    //密码
    private String password;
    //名字
    private String name;


}
