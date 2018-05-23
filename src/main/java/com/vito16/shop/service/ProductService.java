/**
 * 
 */
package com.vito16.shop.service;

import com.vito16.shop.common.Page;
import com.vito16.shop.model.Product;
import com.vito16.shop.model.ProductType;
import com.vito16.shop.repository.ProductRepository;
import com.vito16.shop.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class ProductService {

	@Autowired
    ProductTypeRepository productTypeDao;

	@Autowired
	ProductRepository productDao;

	public void saveType(ProductType type) {
		productTypeDao.save(type);
	}

	public List<ProductType> findType() {
		return productTypeDao.findAll();
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public Product findById(Integer id) {
		return productDao.findOne(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public List<Product> findNew() {
		return productDao.findByOrderByCreateTimeDesc();
	}
    public List<Product> findOld() {
        return productDao.findByOrderByCreateTimeAsc();
    }

	public List<Product> findPop(){
		return productDao.findPopProducts();
	}


    public List<Product> findProducts(Page<Product> page) {
        page.setResult(productDao.findAll(page.getPageable()).getContent());
        page.setTotalCount(productDao.count());
        return page.getResult();
    }

//    public void deleteProduct(Integer id){
//		productTypeDao.deleteByProductId(id);
//		productDao.delete(id);
//
//	}
public void deleteProduct(Integer productId) {
	productDao.delete(productId);
}



}
