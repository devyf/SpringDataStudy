package com.fengye.springdata.daotest;

import com.fengye.springdata.dao.ArticleDao;
import com.fengye.springdata.dao.ArticleDataDao;
import com.fengye.springdata.dao.CommentDao;
import com.fengye.springdata.dao.TypeDao;
import com.fengye.springdata.domain.Article;
import com.fengye.springdata.domain.ArticleData;
import com.fengye.springdata.domain.Comment;
import com.fengye.springdata.domain.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

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

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TypeDao typeDao;

    /***
     *  一对一文章与文章内容关系测试
     */
    @Test
    public void testOnetoOneSave(){
        Article article = new Article();
        article.setAuthor("一对一测试");
        article.setTitle("一对一关系");
        article.setCreateTime(new Date());
        ArticleData articleData = new ArticleData();
        articleData.setContent("小猪佩奇");
        article.setArticleData(articleData);
        articleDao.save(article);
    }

    /**
     * 测试一对多文章与文章评论的关系
     */
    @Test
    public void testOnetoManySave(){
        Article article = new Article();
        article.setAuthor("一对多测试");
        article.setTitle("一对多关系");
        article.setCreateTime(new Date());

        Comment comment1 = new Comment();
        comment1.setComment("好文章！");
        Comment comment2 = new Comment();
        comment2.setComment("不错不错！");

        //建立两者关系
        article.getCommentSet().addAll(Arrays.asList(comment1, comment2));
        articleDao.save(article);
        commentDao.save(comment1);
        commentDao.save(comment2);
    }

    @Test
    public void testManytoManySave(){
        Article article1 = new Article();
        article1.setAuthor("张某");
        article1.setTitle("民事要问关于疫情防控");
        article1.setCreateTime(new Date());

        Article article2 = new Article();
        article2.setAuthor("李某");
        article2.setTitle("军事要问关于台湾独立");
        article2.setCreateTime(new Date());

        Type type1 = new Type();
        type1.setName("军事");
        Type type2 = new Type();
        type2.setName("民事");

        //建立关系
        HashSet<Type> types = new HashSet<>();
        types.add(type1);
        types.add(type2);
        article1.setTypes(types);
        article2.setTypes(types);

        HashSet<Article> articles = new HashSet<>();
        articles.add(article1);
        articles.add(article2);
        type1.setArticleSet(articles);
        type2.setArticleSet(articles);

       articleDao.save(article1);
       articleDao.save(article2);

       typeDao.save(type1);
       typeDao.save(type2);
    }
}
