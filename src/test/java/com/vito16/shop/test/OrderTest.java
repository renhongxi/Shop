package com.vito16.shop.test;

import com.vito16.shop.model.Order;
import com.vito16.shop.model.OrderItem;
import com.vito16.shop.model.Product;
import com.vito16.shop.model.UserAddress;
import com.vito16.shop.service.OrderService;
import com.vito16.shop.service.ProductService;
import com.vito16.shop.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class OrderTest extends BaseTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Test
    public void testOrdering(){
        List<Product> productList = productService.findAll();
        Order order = new Order();
        order.setCreateTime(new Date());
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for(Product pro:productList){
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(2);
            orderItem.setOrder(order);
            orderItem.setProduct(pro);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        UserAddress ua = new UserAddress();
        ua.setAddress("asdfasdf");
        orderService.addOrder(order,orderItems,ua);
    }

    @Test
    public void testDelOrder(){
        //orderService.deleteOrder(5);
    }
}
