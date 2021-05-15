package com.fengye.springdata.daotest;

import com.fengye.springdata.dao.ArticleDao;
import com.fengye.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 测试父接口查询方法
 * @Author: huang
 * @Date: 2021/5/15 9:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class SpringDataJpaQueryTest {
    @Autowired
    private ArticleDao articleDao;

    //主键查询
    @Test
    public void testFindById(){
        Optional<Article> optional = articleDao.findById(3);
        Article article = optional.orElse(null);
        System.out.println(article);

        //根据多个主键查询
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(9);
        list.add(10);
        List<Article> articles = articleDao.findAllById(list);
        for (Article item : articles) {
            System.out.println(item);
        }
    }

    //查询所有
    @Test
    public void testFindAll(){
        List<Article> articles = articleDao.findAll();
        articles.forEach(System.out::println);
    }

    //查询所有--排序
    @Test
    public void testFindAllBySorted(){
        List<Article> articles = articleDao.findAll(Sort.by(Sort.Order.desc("aid")));
        articles.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testFindAllwithPage(){
        //分页从第几页开始查询，每页显示多少条
        Pageable pageable = PageRequest.of(0, 3);
        Page<Article> articlePage = articleDao.findAll(pageable);
        //总记录数  总页数  每页多少
        System.out.println("总记录数:" + articlePage.getTotalElements());
        System.out.println("总页数:" + articlePage.getTotalPages());
        System.out.println("每页多少:" + articlePage.getSize());
        articlePage.forEach(System.out::println);
    }

    //分页查询--并排序
    @Test
    public void testFindAllwithPageAndSort(){
        Sort sort = Sort.by(Sort.Order.desc("aid"));
        //分页从第几页开始查询，每页显示多少条
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<Article> articlePage = articleDao.findAll(pageable);
        //总记录数  总页数  每页多少
        System.out.println("总记录数:" + articlePage.getTotalElements());
        System.out.println("总页数:" + articlePage.getTotalPages());
        System.out.println("每页多少:" + articlePage.getSize());
        articlePage.forEach(System.out::println);
    }
}
