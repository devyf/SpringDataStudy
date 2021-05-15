package com.fengye.springdata.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description:  文章评论表
 * @Author: huang
 * @Date: 2021/5/15 16:11
 */
@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    private Article article;

}
