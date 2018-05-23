package com.vito16.shop.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vito16.shop.common.AppConfig;
import com.vito16.shop.common.Page;
import com.vito16.shop.common.web.JsonResult;
import com.vito16.shop.model.User;
import com.vito16.shop.repository.UserRepository;
import com.vito16.shop.service.UserAddressService;
import com.vito16.shop.service.UserService;
import com.vito16.shop.util.AdminUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin/user")
public class UserAdminController {
    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    AppConfig appConfig;
    @Autowired
    UserService userService;
    @Autowired
    UserAddressService userAddressService;
    @Autowired
    UserRepository userRepository;


    @RequestMapping( method = RequestMethod.GET )
    public String index() {
        return "user/index";
    }

    @RequestMapping( value = "/reg", method = RequestMethod.GET )
    public String reg() {
        return "user/userReg";
    }

    /**
     * 用户管理
     */
    @RequestMapping( value = "/userAdmin", method = RequestMethod.GET )
    public String user(Model model, HttpServletRequest request) {
        Page<User> page = new Page<User>(request);
        userService.findList(page);
        model.addAttribute("page", page);
        return "admin/user/userAdmin";
    }

    /**
     * 添加用户
     */


    //get请求，访问添加用户页面
    @RequestMapping( value = "/add", method = RequestMethod.GET )
    public String addUser() {
        //转到admin/user/userAdd.jsp页面
        return "admin/user/userAdd";
    }

    @RequestMapping( value = "/add", method = RequestMethod.POST )
    public String addUser(@Valid User user, Model model, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError or : result.getAllErrors()) {
                logger.warn("验证类型:{}\n错误消息:{}", or.getCode(), or.getDefaultMessage());
            }
            model.addAttribute("error", "数据错误,请重试");
            return "user/userReg";
        }
        userService.save(user);
        logger.info("成功添加用户:{}", user);
        return "redirect:/admin/user/userAdmin";
    }

    /**
     * 删除用户
     */
    @RequestMapping( value = "userAdmin/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult userDelete(@PathVariable( "id" ) Integer id) {
        userService.deleteUser(id);
        logger.debug("用户删除成功...");

        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }


    /**
     * 修改用户
     */

////更新用户信息页面
//    @RequestMapping( value = "update/{id}", method = RequestMethod.GET )
//    public String updateUser(@PathVariable( "id" ) Integer userId, ModelMap modelMap) {
//
//        //找到userId所表示的用户
//        User user = userRepository.findOne(userId);
//
//        // 传递给请求页面
//        modelMap.addAttribute("user", user);
//        return "admin/user/userUpdate";
//    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(ModelAndView model, @PathVariable Integer id) {
        User user = userService.findOne(id);
        model.addObject("user", user);
        model.setViewName("admin/user/userUpdate");
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView doUpdate(ModelAndView model, User user) {

       // user.setInputUser(AdminUtil.getAdminFromSession(session));
        userService.save(user);
        model.setViewName("redirect:/admin/user/userAdmin");
        return model;
    }


}