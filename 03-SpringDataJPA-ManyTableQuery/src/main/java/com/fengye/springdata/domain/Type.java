package com.fengye.springdata.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 多对多关系，文章类型实体类
 * @Author: huang
 * @Date: 2021/5/15 17:08
 */
@Data
@Table(name = "type")
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    private String name;
    //Type表关联少，选择它来维护关系
    @ManyToMany
    @JoinTable(
            //代表中间表名称
            name = "article_type",
            //中间表的外键对应到当前表的主键的名称
            joinColumns ={@JoinColumn(name = "tid", referencedColumnName = "tid")},
            //中间表的外键对应到对方表的主键的名称
            inverseJoinColumns ={@JoinColumn(name = "aid", referencedColumnName = "aid")}
    )
    private Set<Article> articleSet = new HashSet<>();
}
