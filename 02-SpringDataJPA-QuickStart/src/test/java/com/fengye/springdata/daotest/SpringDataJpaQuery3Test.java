package com.fengye.springdata.daotest;

import com.fengye.springdata.dao.ArticleDao;
import com.fengye.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 测试JPQL语句查询
 * @Author: huang
 * @Date: 2021/5/15 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SpringDataJpaQuery3Test {
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testfindByCondition1(){
        List<Article> byCondition1 = articleDao.findByCondition1("测试员工1", "富士康集团1仓");
        byCondition1.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition2(){
        List<Article> byCondition2 = articleDao.findByCondition2("测试员工1", "富士康集团1仓");
        byCondition2.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition3(){
        List<Article> byCondition3 = articleDao.findByCondition3( "富士康");
        byCondition3.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition4(){
        List<Article> byCondition4 = articleDao.findByCondition4( "富士康");
        byCondition4.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition5(){
        Pageable pageable = PageRequest.of(0, 2);
        Page<Article> articlePage = articleDao.findByCondition5(pageable, "富士康");
        articlePage.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition6(){
        List<Article> byCondition6 = articleDao.findByCondition6(Arrays.asList(7, 8, 9));
        byCondition6.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition7(){
        Article article = new Article();
        article.setTitle("富士康集团3仓");
        article.setAuthor("测试员工3");
        List<Article> byCondition7 = articleDao.findByCondition7(article);
        byCondition7.forEach(System.out::println);
    }

    @Test
    public void testfindByCondition8(){
        List<Article> byCondition8 = articleDao.findByCondition8("%3%", "新员工阿三");
        byCondition8.forEach(System.out::println);
    }

}
