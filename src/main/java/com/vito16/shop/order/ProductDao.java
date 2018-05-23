package com.vito16.shop.order;

import com.vito16.shop.model.Product;

import java.util.List;

public interface ProductDao {
    public Product getProduct(int id);

    public Product getProduct(String title);

    public List<Product> getProductsByKeyWord(String seacrhKeyWord);

}
