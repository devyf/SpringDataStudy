package com.fengye.jpatest;

import com.fengye.jpa.domain.Article;
import com.fengye.jpa.utils.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/14 12:17
 */
public class JPACRUDTest {
    /**
     * 测试查询：查询单个
     */
    @Test
    public void testFindById(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        //1.获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //2.开启事务
        transaction.begin();
        //3.执行查询操作
        Article article = entityManager.find(Article.class, 1);
        System.out.println(article);
        //4.提交事务
        transaction.commit();
        //5.关闭连接
        JPAUtil.close(entityManager);
    }

    /**
     * 测试查询：查询所有
     */
    @Test
    public void testFindAll(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        //1.获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //2.开启事务
        transaction.begin();
        //3.执行查询操作
        Query query = entityManager.createQuery("select a.aid, a.author, a.createTime, a.title from Article a");
        List<Article> articleList = new ArrayList<>();
        query.getResultList().forEach((item) -> {
            Object[] objArr = (Object[]) item;
            Article article = new Article();
            article.setAid((Integer) objArr[0]);
            article.setAuthor((String) objArr[1]);
            article.setCreateTime((Date) objArr[2]);
            article.setTitle((String) objArr[3]);
            articleList.add(article);
        });
        articleList.forEach(System.out::println);
        //4.提交事务
        transaction.commit();
        //5.关闭连接
        JPAUtil.close(entityManager);
    }

    /**
     * 测试添加
     */
    @Test
    public void testInsert(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Article article = new Article();
        article.setTitle("测试文章1");
        article.setAuthor("测试人员1");
        article.setCreateTime(new Date());

        entityManager.persist(article);

        transaction.commit();
        JPAUtil.close(entityManager);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //修改时需要先查询，查询完成之后再修改
        Article article = entityManager.find(Article.class, 1);
        article.setAuthor("枫夜666");
        article.setCreateTime(new Date());
        //执行修改语句
        entityManager.merge(article);

        transaction.commit();
        JPAUtil.close(entityManager);
    }

    @Test
    public void testDelete(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //先查询到要删除的对象，然后执行remove进行删除
        Article article = entityManager.find(Article.class, 1);
        entityManager.remove(article);

        transaction.commit();
        JPAUtil.close(entityManager);
    }
}
