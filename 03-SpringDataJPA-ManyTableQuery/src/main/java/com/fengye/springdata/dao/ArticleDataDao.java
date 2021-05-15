package com.fengye.springdata.dao;

import com.fengye.springdata.domain.ArticleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 *   JpaRepository<实体类类型 ， 主键类型>：用来完成基本的CRUD操作
 *  JpaSpecificationExecutor<实体类型>：用于复杂查询（分页等查询操作）
 * @Author: huang
 * @Date: 2021/5/15 15:35
 */
public interface ArticleDataDao extends JpaRepository<ArticleData, Integer>, JpaSpecificationExecutor<ArticleData> {
}
