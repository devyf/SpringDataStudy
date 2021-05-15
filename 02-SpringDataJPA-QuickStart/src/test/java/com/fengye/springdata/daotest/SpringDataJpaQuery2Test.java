package com.fengye.springdata.daotest;

import com.fengye.springdata.dao.ArticleDao;
import com.fengye.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description:测试根据方法自定义命名规则查询
 * @Author: huang
 * @Date: 2021/5/15 10:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SpringDataJpaQuery2Test {
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testDaoInterfaceMethod(){
        List<Article> articles = articleDao.findByTitle("富士康集团1仓");
        articles.forEach(System.out::println);

        List<Article> articles1 = articleDao.findByTitleIsLike("%集团%");
        articles1.forEach(System.out::println);

        List<Article> articles2 = articleDao.findArticleByTitleAndAuthor("我是新外包员工777", "新员工阿七");
        articles2.forEach(System.out::println);

        List<Article> articles3 = articleDao.findArticleByAidBetween(1, 10);
        articles3.forEach(System.out::println);

        List<Article> articles4 = articleDao.findArticleByAidLessThanEqual(9);
        articles4.forEach(System.out::println);

        List<Article> articles5 = articleDao.findArticleByAidIn(Arrays.asList(1, 3, 5, 7, 9));
        articles5.forEach(System.out::println);

        String str = "2021-05-15 06:36:19";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        Date date = Date.from(dateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
        List<Article> articles6 = articleDao.findArticleByCreateTimeAfter(date);
        articles6.forEach(System.out::println);
    }
}
