package com.fengye.springdata.dao;

import com.fengye.springdata.domain.Comment;
import com.fengye.springdata.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/15 18:27
 */
public interface TypeDao extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type> {
}
