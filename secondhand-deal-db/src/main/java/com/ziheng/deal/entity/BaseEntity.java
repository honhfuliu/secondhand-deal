package com.ziheng.deal.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private String createUser;
    private Date createTime;
    private String  modifiedUser;
    private Date modifiedTime;
}
