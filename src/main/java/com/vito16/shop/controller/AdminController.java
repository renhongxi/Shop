package com.vito16.shop.controller;

import com.vito16.shop.model.Admin;
import com.vito16.shop.service.*;
import com.vito16.shop.util.AdminUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @Autowired
    OrderService orderService;


    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    /**
     *
     * 管理员注册
     *
     */

    @RequestMapping(value = "/rege", method = RequestMethod.GET)
    public String rege() {
        return "admin/adminReg";
    }

//    @RequestMapping(value = "/rege", method = RequestMethod.POST)
//    public String doRege(Admin admin, HttpSession session) {
//        adminService.save(admin);
//        return "redirect:/admin/adminReg";
//    }
    @RequestMapping( value = "/rege", method = RequestMethod.POST )
    public String doRege(@Valid Admin admin, Model model, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError or : result.getAllErrors()) {
                logger.warn("验证类型:{}\n错误消息:{}", or.getCode(), or.getDefaultMessage());
            }
            model.addAttribute("error", "数据错误,请重试");
            return "admin/adminReg";
        }
        adminService.save(admin);
        logger.info("成功添加用户:{}", admin);
        return "redirect:/admin/login";
    }

    /**
     *
     * 管理员登录
     *
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/adminLogin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(Admin admin, HttpSession session) {
        if (adminService.checkLogin(admin)) {
            AdminUtil.saveAdminToSession(session, adminService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword()));
            logger.debug("管理员[{}]登陆成功",admin.getUsername());
            return "redirect:/admin/product";
        }
        return "redirect:/admin/login?errorPwd=true";
    }

    /**
     *
     * 管理员登出
     *
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        AdminUtil.deleteAdminFromSession(session);
        return "redirect:/admin/login";
    }









//    @RequestMapping(value = "/user/delete/{id}")
//    @ResponseBody
//    public JsonResult productDelete(@PathVariable("id") Integer id) {
//        productService.deleteProduct(id);
//        JsonResult result = new JsonResult();
//        result.setToSuccess();
//        return result;
//    }


}
