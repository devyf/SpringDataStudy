package com.fengye.springdata.dao;

import com.fengye.springdata.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 *    JpaRepository<实体类类型，主键类型>：用来完成基本的CRUD操作
 *    JpaSpecificationExecutor<实体类型>：用于复杂查询（分页等查询操作）
 * @Author: huang
 * @Date: 2021/5/15 5:44
 */
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
    //标题字段查询
    List<Article> findByTitle(String title);

    //模糊查询
    List<Article> findByTitleIsLike(String title);

    //根据标题和作者查询
    List<Article> findArticleByTitleAndAuthor(String title, String author);

    //根据ID范围查询
    List<Article> findArticleByAidBetween(Integer startId, Integer endId);

    List<Article> findArticleByAidLessThanEqual(Integer endId);

    List<Article> findArticleByAidIn(List<Integer> ids);

    //根据创建时间之后查询
    List<Article> findArticleByCreateTimeAfter(Date createTime);

    //JPQL查询语句：=========================================================
    @Query("from Article a where a.author=?1 and a.title=?2")
    List<Article> findByCondition1(String author, String title);

    @Query("from Article a where a.author=:author and a.title=:title")
    List<Article> findByCondition2(@Param("author") String author, @Param("title") String title);

    //展示like模糊查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition3(@Param("title") String title);

    @Query("from Article a where a.title like %:title% order by a.aid desc")
    List<Article> findByCondition4(@Param("title") String title);

    //展示分页查询
    @Query("from Article a where a.title like %:title%")
    Page<Article> findByCondition5(Pageable pageable, @Param("title") String title);

    //展示传入集合参数查询
    @Query("from Article a where a.aid in :aids")
    List<Article> findByCondition6(@Param("aids") List<Integer> aids);

    //展示传入Bean对象查询
    @Query("from Article a where a.author=:#{#article.author} and a.title=:#{#article.title}")
    List<Article> findByCondition7(@Param("article") Article article);

    //本地SQL查询=============================================================================
    //nativeQuery=true表示使用本地SQL查询
    //基本不会使用，除非是出现非常复杂的业务情况导致SQL非常复杂，JPQL搞不定的时候
    @Query(value = "select * from article where title like ?1 and author = ?2", nativeQuery = true)
    List<Article> findByCondition8(String title,String author);
}
