package com.fengye.jpa.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/14 11:16
 */
@Data
//@Table建立实体类和数据表的关系  name表示表名称
//使用@Table注解建立实体类和数据表之间的对应关系
@Table(name = "article")
//表示这是一个实体类
@Entity
public class Article implements Serializable {
    //声明aid为主键字段
    @Id
    //设置主键生成策略：IDENTITY表示自增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;  //主键
    //声明类的属性名与数据库表中的字段名对应关系
    //如果属性名与字段名一致，此注解可以省略
    @Column(name = "author")
    private String author;  //作者
    private Date createTime;   //创建时间
    private String title;    //标题
}
