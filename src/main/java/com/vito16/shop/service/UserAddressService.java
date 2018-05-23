/**
 *
 */
package com.vito16.shop.service;

import com.vito16.shop.common.Page;
import com.vito16.shop.model.User;
import com.vito16.shop.model.UserAddress;
import com.vito16.shop.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressDao;

    /**
     * 保存收货地址
     *
     * @param userAddress
     */
    public void save(UserAddress userAddress) {
        userAddressDao.save(userAddress);
    }

    /**
     * 查询指定ID的收货地址
     *
     * @param id
     * @return
     */
    public UserAddress findById(Integer id) {
        return userAddressDao.findOne(id);
    }

    /**
     * 查询用户关联收货地址
     *
     * @param userId 用户ID
     * @return
     */
    public List<UserAddress> findByUserId(Integer userId) {
        User user = new User();
        user.setId(userId);
        return userAddressDao.findByUser(user);
    }

    public List<UserAddress> findUserAddress(Page<UserAddress> page){
        page.setResult(userAddressDao.findAll(page.getPageable()).getContent());
        page.setTotalCount(userAddressDao.count());
        return page.getResult();
    }




    /**
     * 删除收货地址
     *
     * @param id 收货地址ID
     */
    public void deleteById(Integer id) {
        userAddressDao.delete(id);
    }

    /**
     * 修改地址
     * @param userAddress
     */
    public void editUserAddress(UserAddress userAddress) {
        userAddressDao.save(userAddress);
    }
}
