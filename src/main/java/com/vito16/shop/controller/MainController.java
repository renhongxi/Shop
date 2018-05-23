package com.vito16.shop.controller;

import com.alibaba.fastjson.JSONArray;
import com.vito16.shop.model.Product;
import com.vito16.shop.model.User;
import com.vito16.shop.repository.UserRepository;
//import com.vito16.shop.service.ProductService;
import  com.vito16.shop.order.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	ProductService productService;

	@Autowired
	UserRepository userRepository;

/*	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model = new ModelAndView("index");
		model.addObject("newProductList", productService.findNew());
		model.addObject("popProductList", productService.findPop());
		model.addObject("productList", productService.findAll());
		model.addObject("productTypeList", productService.findType());
		return model;
	}
*/
@RequestMapping(value = "/",method = RequestMethod.GET)
	public String index(){
		return "index";
}

@RequestMapping(value = "/admin/uesr/userAdmin",method = RequestMethod.GET)
	public String getUser(ModelMap modelMap){
		//查询user表中所有记录
	List<User> userList =userRepository.findAll();
	//将所有记录传递给要返回的jsp页面，放在userList当中
	modelMap.addAttribute("userList",userList);
	//返回views目录下的admin/user/userAdmin.jsp页面
     return "admin/user/userAdmin";
	}



	/**
	 *
	 * 搜索功能
	 *
	 */

	@RequestMapping(value = "/productDetail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> productDetail(int id, HttpSession httpSession) {
		System.out.println("I am here!"+id);
		Product product = productService.getProduct(id);
		httpSession.setAttribute("productDetail",product);
		System.out.print("I am here"+product.getTitle());
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result","success");
		return resultMap;
	}

	@RequestMapping(value = "/searchPre", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> searchPre(String searchKeyWord, HttpSession httpSession) {
		httpSession.setAttribute("searchKeyWord",searchKeyWord);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result","success");
		return resultMap;
	}


	@RequestMapping(value = "/product_detail")
	public String product_detail() {
		return "product/productList";
	}

	@RequestMapping(value = "/search")
//	public String search(String searchKeyWord) {
	public String search(){
		return "search/search";
	}

	@RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> searchProduct(String searchKeyWord){
		System.out.println("我到了SearchProduct"+searchKeyWord);
		List<Product> productList = new ArrayList<Product>();
		productList = productService.getProductsByKeyWord(searchKeyWord);
		String searchResult = JSONArray.toJSONString(productList);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result",searchResult);
		System.out.println("我返回了"+searchResult);
		return resultMap;
	}


	}


