/**
 * 
 */
package com.vito16.shop.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 *用户账号
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id; //数据序号
	private String account;//账号
	private String username;//用户名
	private Long balance;//账户余额
	private Long point;//积分值
	private String phone;//手机号码
	private String telPhone;//座机电话
	private String address;//地址
	private String zipCode;//邮编
	private String remark;//备注
	private String password;//密码信息（加密）
	private List<UserAddress> addresses; // 关联收货地址
	private List<Order> orders;// 订单
    private String slat;//密码加密盐

	public User() {
	};

	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Length(min = 2, max = 15)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty
	@Length(min = 6, max = 14)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Max(999999)
	@Min(0)
	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	@Length(min = 11, max = 11)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min = 11, max = 11)
	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	@Length(min = 4, max = 50)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min = 6, max = 6)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Length(max = 50)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(mappedBy="user")
	public List<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<UserAddress> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(mappedBy = "user")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }



}
