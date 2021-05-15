package com.fengye.springdata.daotest;

import com.fengye.springdata.dao.ArticleDao;
import com.fengye.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 针对SpringData 整合进行简单Api测试
 * @Author: huang
 * @Date: 2021/5/15 5:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SimpleDataJPAApiTest {
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testSave(){
        Article article = new Article();
        article.setAuthor("测试员工1");
        article.setCreateTime(new Date());
        article.setTitle("富士康集团1仓");
        //保存一个实体
        articleDao.save(article);

        //保存一个实体,并且立即刷新缓存
        //articleDao.saveAndFlush(article);
    }

    @Test
    public void testSaveAll(){
        Article article = new Article();
        article.setAuthor("测试员工1");
        article.setCreateTime(new Date());
        article.setTitle("富士康集团1仓");

        Article article2 = new Article();
        article2.setAuthor("测试员工2");
        article2.setCreateTime(new Date());
        article2.setTitle("富士康集团2仓");

        Article article3 = new Article();
        article3.setAuthor("测试员工3");
        article3.setCreateTime(new Date());
        article3.setTitle("富士康集团3仓");

        List<Article> articles = Arrays.asList(article, article2, article3);
        //保存多个实体
        articleDao.saveAll(articles);
    }

    @Test
    public void testDeleteById(){
        //1  根据主键删除
        articleDao.deleteById(1);

        //2 根据实体删除,但是这个实体必须要有主键
        Article article = new Article();
        article.setAid(3);

        articleDao.delete(article);
    }

    @Test
    public void testDeleteAll(){
        Article article2 = new Article();
        article2.setAid(5);
        Article article3 = new Article();
        article3.setAid(6);
        List list = new ArrayList();
        list.add(article2);
        list.add(article3);
        //1.deleteAll步骤：select--insert--delete，先一条一条查询，再insert，再删除
        //articleDao.deleteAll(list);
        //2.deleteInBatch步骤：delete，只发送一条sql，性能高
        articleDao.deleteInBatch(list);
    }


    @Test
    public void testUpdate(){
        Article newArticle = new Article();
        //修改方法与新增方法调用的都是同一个方法save()，只是修改必须传入id
        newArticle.setAid(3);
        newArticle.setAuthor("新员工");
        newArticle.setCreateTime(new Date());
        newArticle.setTitle("我是新外包员工");
        articleDao.save(newArticle);
    }

    @Test
    public void testUpdateAll(){
        Article newArticle1 = new Article();
        newArticle1.setAid(3);
        newArticle1.setAuthor("新员工阿三");
        newArticle1.setCreateTime(new Date());
        newArticle1.setTitle("我是新外包员工333");
        Article newArticle2 = new Article();
        newArticle2.setAid(7);
        newArticle2.setAuthor("新员工阿七");
        newArticle2.setCreateTime(new Date());
        newArticle2.setTitle("我是新外包员工777");
        articleDao.saveAll(Arrays.asList(newArticle1, newArticle2));
    }
}
