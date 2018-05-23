package com.vito16.shop.controller;

import com.vito16.shop.common.AppConfig;
import com.vito16.shop.common.Constants;
import com.vito16.shop.common.Page;
import com.vito16.shop.common.web.JsonResult;
import com.vito16.shop.model.Order;
import com.vito16.shop.model.Remember;
import com.vito16.shop.model.User;
import com.vito16.shop.model.UserAddress;
import com.vito16.shop.service.*;
import com.vito16.shop.util.CookieUtil;
import com.vito16.shop.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.vito16.shop.util.UserUtil.getUserFromSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    AppConfig appConfig;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserAddressService userAddressService;
    //    @Autowired
//    UserProfileService userProfileService;
    @Autowired
    RememberService rememberService;

    @RequestMapping( method = RequestMethod.GET )
    public String index() {
        return "user/index";
    }

    /**
     * 用户注册
     */

    @RequestMapping( value = "/reg", method = RequestMethod.GET )
    public String reg() {
        return "user/userReg";
    }

    @RequestMapping( value = "/reg", method = RequestMethod.POST )
    public String doReg(@Valid User user, Model model, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError or : result.getAllErrors()) {
                logger.warn("验证类型:{}\n错误消息:{}", or.getCode(), or.getDefaultMessage());
            }
            model.addAttribute("error", "数据错误,请重试");
            return "user/userReg";
        }
        userService.save(user);
        logger.info("成功添加用户:{}", user);
        return "redirect:/user/login";
    }

    /**
     * 用户登录
     */

    @RequestMapping( value = "/login", method = RequestMethod.GET )
    public String loginForm(HttpServletRequest request, HttpSession session) {
        String uuid;
        if (StringUtils.isNotBlank(uuid = CookieUtil.getCookieValue(request, appConfig.USER_COOKIE_NAME))) {
            Remember remember = rememberService.findById(uuid);
            if (remember != null && remember.getUser() != null) {
                if (userService.checkLogin(remember.getUser())) {
                    UserUtil.saveUserToSession(session, remember.getUser());
                    logger.info("用户[{}]使用cookie登录成功.", remember.getUser().getUsername());
                    return "redirect:/";
                }
            }
        }
        return "user/userLogin";
    }

    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (userService.checkLogin(user)) {
            user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            UserUtil.saveUserToSession(session, user);
            logger.info("是否记住登录用户：{}", request.getParameter("remember"));

            if ("on".equals(request.getParameter("remember"))) {
                String uuid = UUID.randomUUID().toString();
                Remember remember = new Remember();
                remember.setId(uuid);
                remember.setUser(user);
                remember.setAddTime(new Date());
                rememberService.add(remember);
                CookieUtil.addCookie(response, appConfig.USER_COOKIE_NAME, uuid, appConfig.USER_COOKIE_AGE);
            } else {
                CookieUtil.removeCookie(response, appConfig.USER_COOKIE_NAME);
            }
            logger.info("用户[{}]登陆成功", user.getUsername());
            return "redirect:/";
        }
        return "redirect:/user/login?errorPwd=true";
    }

    /**
     * 用户登出
     */

    @RequestMapping( value = "/logout", method = RequestMethod.GET )
    public String logout(HttpSession session, HttpServletResponse response) {
        UserUtil.deleteUserFromSession(session);
        CookieUtil.removeCookie(response, appConfig.USER_COOKIE_NAME);
        return "redirect:/user/login";
    }

    /**
     * 个人信息
     */

    @RequestMapping( value = "/profile" )
    public String profile(HttpSession session, Model model) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "redirect:/user/login?timeout=true";
        }
        model.addAttribute("user", user);
        return "user/userProfile";
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ModelAndView viewUser(@PathVariable Integer id, ModelAndView model, HttpServletRequest request) {
//        User user = userService.findOne(id);
//        model.addObject("user", user);
//        model.setViewName("user/userUpdate");
//        return model;
//    }

    /**
     * 订单列表
     */
    @RequestMapping( value = "/order", method = RequestMethod.GET )
    public String orderList(Model model, HttpSession session, HttpServletRequest request) {
        User user = getUserFromSession(session);
        org.springframework.util.Assert.notNull(user, "未登录用户，非法操作");
        Page<Order> page = new Page<>(request);
        orderService.findOrders(page, user.getId());
        model.addAttribute("page", page);
        return "order/orderList";
    }

    /**
     * 订单查看
     */
    @RequestMapping( value = "/order/{id}", method = RequestMethod.GET )
    public String orderView(@PathVariable Integer id, Model model, HttpSession session, HttpServletRequest request) {
        User user = getUserFromSession(session);
        org.springframework.util.Assert.notNull(user, "未登录用户，非法操作");
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/orderView";
    }

    /**
     * 确认收货
     */
    @RequestMapping( value = "/order/confirm/{id}" )
    @ResponseBody
    public JsonResult orderConfirm(@PathVariable Integer id, Model model, HttpSession session, HttpServletRequest request) {
        User user = getUserFromSession(session);
        org.springframework.util.Assert.notNull(user, "未登录用户，非法操作");
        Order order = orderService.findById(id);

        JsonResult result = new JsonResult();
        if (Objects.equals(order.getUser().getId(), user.getId())) {
            orderService.updateOrderStatus(id, Constants.OrderStatus.ENDED);
            result.setToSuccess();
        } else {
            result.setToFail();
        }
        return result;
    }

    /**
     * 用户地址加载
     */
    @RequestMapping( value = "/userAddressList/{id}" )
    @ModelAttribute
    @ResponseBody
    public List<UserAddress> getUserAddressList(@PathVariable int id) {
        return userAddressService.findByUserId(id);
    }

    @RequestMapping( value = "/userAddress", method = RequestMethod.GET )
    public String userAddress(Model model, HttpSession session,HttpServletRequest request) {
        Page<UserAddress> page = new Page<UserAddress>(request);
        userAddressService.findUserAddress(page);
        model.addAttribute("page",page);
        model.addAttribute("title", "地址管理");
        List<UserAddress> userAddressList = userAddressService.findByUserId(getUserFromSession(session).getId());
        model.addAttribute("userAddressList", userAddressList);
        return "user/userAddress";
    }

//    @RequestMapping( value = "/order", method = RequestMethod.GET )
//    public String orderList(Model model, HttpSession session, HttpServletRequest request) {
//        User user = getUserFromSession(session);
//        org.springframework.util.Assert.notNull(user, "未登录用户，非法操作");
//        Page<Order> page = new Page<>(request);
//        orderService.findOrders(page, user.getId());
//        model.addAttribute("page", page);
//        return "order/orderList";
//  }


    /**
     * 添加地址
     */

    @RequestMapping( value = "/userAddress/add", method = RequestMethod.GET )
    public String addUserAddress(Model model) {
        model.addAttribute("title", "添加收货地址");
        return "user/userAddAddress";
    }

    @RequestMapping(value = "/userAddress/add",method = RequestMethod.POST)
    public String doAdd(UserAddress userAddress,HttpSession session){
        userAddress.setUser(getUserFromSession(session));
        userAddressService.save(userAddress);
        return "redirect:/user/userAddress";
    }

    /**
     * 修改地址
     */

    @RequestMapping( value = "/userAddress/edit/{id}", method = RequestMethod.GET )
    public ModelAndView userEdit(ModelAndView model, @PathVariable Integer id) {
        UserAddress userAddress = userAddressService.findById(id);
        model.addObject("userAddress", userAddress);
        model.setViewName("user/userEditAddress");
        return model;
    }

    @RequestMapping( value = "/userAddress/edit", method = RequestMethod.POST )
    public ModelAndView doUserEdit(ModelAndView model, UserAddress userAddress,HttpSession session) {
        userAddress.setUser(getUserFromSession(session));
        userAddressService.save(userAddress);
        model.setViewName("redirect:/user/userAddress");
        return model;
    }

    /**
     * 查看地址
     */

    @RequestMapping( value = "/userAddress/{id}" )
    @ResponseBody
    public UserAddress findAddress(@PathVariable Integer id) {
        return userAddressService.findById(id);
    }

    /**
     * 删除地址
     */

    @RequestMapping( value = "/userAddress/delete/{id}", method = RequestMethod.GET )
    @ResponseBody
    public JsonResult delUserAddress(@PathVariable Integer id) {
        userAddressService.deleteById(id);
        logger.debug("收货地址删除成功...");

        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }

}
