package com.fengye.springdata.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 文章类
 * @Author: huang
 * @Date: 2021/5/15 5:37
 */
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    //mappedBy表示声明自己不是一对多的关系维护端，由对方来维护，是在一的一方进行声明的。mappedBy的值应该为当前类在关系维护方中的属性名
    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private ArticleData articleData;

    @OneToMany(mappedBy = "article")
    private Set<Comment> commentSet = new HashSet<>();

    @ManyToMany(mappedBy = "articleSet")  //mappedBy中的值为当前类在对方Type类中的属性名
    private Set<Type> types = new HashSet<>();

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
