package com.vito16.shop.order;


import com.vito16.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplement implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public Product getProduct(String title) {
        return productDao.getProduct(title);
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return productDao.getProductsByKeyWord(searchKeyWord);
    }
}
