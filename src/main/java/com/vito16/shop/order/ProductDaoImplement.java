package com.vito16.shop.order;

import com.vito16.shop.model.Product;
import com.vito16.shop.order.ProductDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;

@Repository
public class ProductDaoImplement implements ProductDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Product getProduct(int id) {
        String hql = "from Product where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (Product) query.uniqueResult();
    }

    @Override
    public Product getProduct(String title) {
        String hql = "from Product where title=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,title);
        return (Product) query.uniqueResult();
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        String queryKeyWord = "%";
        for (int i=0;i<searchKeyWord.length();i++){
            queryKeyWord += String.valueOf(searchKeyWord.charAt(i)) + "%";
        }
        System.out.println("我搜索了"+queryKeyWord);
        String hql = "from Product where name like ? or key_word like ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,queryKeyWord);
        query.setParameter(1,queryKeyWord);
        return query.list();
    }
}
