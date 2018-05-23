package com.vito16.shop.repository;

import com.vito16.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User DAO
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsernameAndPassword(String username, String password);

    public User  findByUsername(String username);


    @Modifying//说明该方法是修改操作
    @Transactional//说明该方法是事务性操作
    //定义查询
    //@Param注解用于提取参数
    @Query("update  User us set us.username=:qUsername,us.password=:qPassword,us.address=:qAddress,us.phone=:qPhone where us.id=:qId")
    void updateUser(@Param( "qUsername" ) String username, @Param( "qPassword" ) String password,
                    @Param( "qAddress" ) String address, @Param( "qPhone" ) String phone, @Param( "qId" ) Integer id);




}
