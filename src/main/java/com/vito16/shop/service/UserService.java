/**
 * 
 */
package com.vito16.shop.service;

import com.vito16.shop.common.Page;
import com.vito16.shop.model.User;
import com.vito16.shop.repository.ProductRepository;
import com.vito16.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userDao;


	public boolean checkLogin(User user) {
		user = userDao.findByUsernameAndPassword(user.getUsername(),
				user.getPassword());
		return user != null;
	}
	
	public User findByUsernameAndPassword(String username,String password){
		User user = userDao.findByUsernameAndPassword(username, password);
		return user;
	}

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
	
	public void save(User user) {
		userDao.save(user);
	}

	public User findOne(Integer id) {
		return userDao.findOne(id);
	}

	public List<User> findList(Page<User> page) {
		page.setResult(userDao.findAll(page.getPageable()).getContent());
		page.setTotalCount(userDao.count());
		return page.getResult();
	}

	/**
	 *
	 * 删除用户
	 *
	 */
	public void deleteUser(Integer userId) {
		userDao.delete(userId);
	}

}
