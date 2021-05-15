package com.fengye.springdata.dao;

import com.fengye.springdata.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/15 16:42
 */
public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
}
