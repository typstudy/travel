package com.typ.travel.service.impl;

import com.typ.travel.dao.CategoryDao;
import com.typ.travel.dao.impl.CategoryDaoImpl;
import com.typ.travel.entity.Category;
import com.typ.travel.service.CategoryService;
import com.typ.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author typ
 * @date 2019/4/17 15:59
 * @Description: com.typ.travel.service.impl
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从Redis中查询
        //1.1获取Redis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedSet排序
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        //2.判断查询的集合是否为空
        List<Category> category=null;
        if(categorys==null||categorys.size()==0){
            //3.如果为空，则从数据库中查询，再将数据库数据存入Redis
            //3.1从数据库中查询
            category = categoryDao.findAll();
            //3.2 将数据库数据存入Redis
            for (int i = 0; i < category.size(); i++) {

                jedis.zadd("category",category.get(i).getCid(),
                        category.get(i).getCname());
            }
        }else{
            //如果不为空，就将set的数据存入list
            category=new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category categories = new Category();
                categories.setCname(tuple.getElement());
                categories.setCid((int) tuple.getScore());
                category.add(categories);
            }
        }

        return category;
    }
}
