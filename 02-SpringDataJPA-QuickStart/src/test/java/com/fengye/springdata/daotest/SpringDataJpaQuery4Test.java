package com.fengye.springdata.daotest;

import com.fengye.springdata.dao.ArticleDao;
import com.fengye.springdata.domain.Article;
import com.mysql.jdbc.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/15 12:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SpringDataJpaQuery4Test {
    @Autowired
    private ArticleDao articleDao;

    String title = "富士";

    Integer aid = 3;

    /**
     * 查找title中含有“富士”，并且aid>3的文章
     */
    @Test
    public void testPredict() {
        List<Article> articles = articleDao.findAll(new Specification<Article>() {
            /**
             * @param root  代表实体对象，我们可以通过它获取属性值
             * @param cq   用于生成SQL语句
             * @param cb   用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //构造复杂的SQL条件
                if (!StringUtils.isNullOrEmpty(title)) {
                    Predicate predicate = cb.like(root.get("title").as(String.class), "%" + title + "%");
                    predicates.add(predicate);
                }
                if (aid != null) {
                    Predicate predicate = cb.greaterThan(root.get("aid").as(Integer.class), aid);
                    predicates.add(predicate);
                }
                //使用cq来组装Query查询语句
                return cb.and(predicates.toArray(new Predicate[]{}));
            }
        });
        articles.forEach(System.out::println);
    }

    /**
     * 查找title中含有“富士”，并且aid>3的文章，并分页
     */
    @Test
    public void testPredictPage() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Article> articlePage = articleDao.findAll(new Specification<Article>() {
            /**
             * @param root  代表实体对象，我们可以通过它获取属性值
             * @param cq   用于生成SQL语句
             * @param cb   用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //构造复杂的SQL条件
                if (!StringUtils.isNullOrEmpty(title)) {
                    Predicate predicate = cb.like(root.get("title").as(String.class), "%" + title + "%");
                    predicates.add(predicate);
                }
                if (aid != null) {
                    Predicate predicate = cb.greaterThan(root.get("aid").as(Integer.class), aid);
                    predicates.add(predicate);
                }
                //使用cq来组装Query查询语句
                return cb.and(predicates.toArray(new Predicate[]{}));
            }
        }, pageable);
        articlePage.forEach(System.out::println);
    }

    /**
     * 查找title中含有“富士”，并且aid>3的文章，并排序
     */
    @Test
    public void testPredictSorted(){
        Sort sort = Sort.by(Sort.Order.desc("aid"));
        List<Article> articles = articleDao.findAll(new Specification<Article>() {
            /**
             * @param root  代表实体对象，我们可以通过它获取属性值
             * @param cq   用于生成SQL语句
             * @param cb   用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //构造复杂的SQL条件
                if (!StringUtils.isNullOrEmpty(title)) {
                    Predicate predicate = cb.like(root.get("title").as(String.class), "%" + title + "%");
                    predicates.add(predicate);
                }
                if (SpringDataJpaQuery4Test.this.aid != null) {
                    Predicate predicate = cb.greaterThan(root.get("aid").as(Integer.class), SpringDataJpaQuery4Test.this.aid);
                    predicates.add(predicate);
                }
                //使用cq来组装Query查询语句
                return cb.and(predicates.toArray(new Predicate[]{}));
            }
        }, sort);
        articles.forEach(System.out::println);
    }
}
