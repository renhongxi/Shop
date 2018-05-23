package com.vito16.shop.repository;


import com.vito16.shop.model.User;

import java.util.List;

public interface IUserDao {
    public boolean doCreate(User user)throws Exception;
    public List<User> findAll(String keyWord)throws Exception;

    public User findByid(int id) throws Exception;

}
