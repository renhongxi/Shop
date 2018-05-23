package com.vito16.shop.repository;

import com.vito16.shop.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByUsernameAndPassword(String username, String password);
}
