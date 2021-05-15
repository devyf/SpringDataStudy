package com.fengye.springdata.domain;

import com.mysql.jdbc.StringUtils;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description: 文章详情类
 * @Author: huang
 * @Date: 2021/5/15 15:12
 */
@Entity
@Table(name = "article_data")
@Data
public class ArticleData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    @OneToOne
    //使用@JoinColumn声明维护外键关系，当前表中的外键articleId指向对方表的主键aid，unique表示articleId是唯一，一对一的关系
    @JoinColumn(name = "articleId", referencedColumnName = "aid", unique = true)
    private Article article;
}
