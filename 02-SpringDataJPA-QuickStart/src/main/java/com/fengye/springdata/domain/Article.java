package com.fengye.springdata.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/15 5:37
 */
@Data
@Entity  //表示这是一个实体类
@Table(name = "article")  //建立实体类和表之间的映射关系
public class Article implements Serializable {
    @Id  //声明aid为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //配置主键生成策略，为自增策略
    private Integer aid;
    //声明类的属性跟数据库表字段的对应关系，如果属性名和字段名称一致，则可以省略此注解
    @Column(name = "title")
    private String title;
    private String author;
    private Date createTime;
}
