package com.vito16.shop.controller;

import com.vito16.shop.common.Constants;
import com.vito16.shop.common.Page;
import com.vito16.shop.common.web.JsonResult;
import com.vito16.shop.model.Picture;
import com.vito16.shop.model.Product;
import com.vito16.shop.service.PictureService;
import com.vito16.shop.service.ProductService;
import com.vito16.shop.util.AdminUtil;
import com.vito16.shop.util.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductAdminController {
    private static final Logger logger = LoggerFactory.getLogger(ProductAdminController.class);

    @Autowired
    ProductService productService;
    @Autowired
    PictureService pictureService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView admin(ModelAndView model, HttpSession session, HttpServletRequest request) {
        Page<Product> page = new Page<Product>(request);
        productService.findProducts(page);
        model.addObject("page", page);
        model.setViewName("admin/product/productAdmin");
        return model;
    }

    /**
     *
     * 添加商品
     *
     */

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newForm(HttpSession session) {
        if (AdminUtil.getAdminFromSession(session) == null) {
            return "redirect:/admin/login?error=true";
        }
        return "admin/product/productNew";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String doNew(Product product, HttpSession session, @RequestParam("imgFile") MultipartFile file) {
        if (file!=null&&!file.isEmpty()) {
            uploadImage(product, session, file);
        }
        product.setInputUser(AdminUtil.getAdminFromSession(session));
        product.setCreateTime(new Date());
        productService.save(product);
        return "redirect:/admin/product";
    }

    /**
     *
     * 修改商品
     *
     */

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView model, @PathVariable Integer id) {
        Product product = productService.findById(id);
        model.addObject("product", product);
        model.setViewName("admin/product/productEdit");
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(ModelAndView model, Product product, HttpSession session, @RequestParam(name = "file",required = false) MultipartFile file) {
        if (file!=null&&!file.isEmpty()) {
            uploadImage(product, session, file);
        }
        product.setInputUser(AdminUtil.getAdminFromSession(session));
        productService.save(product);
        model.setViewName("redirect:/admin/product");
//        if (!file.isEmpty()) {
//                        String fileName = new Date().getTime()+".jpg";
//                        String path = session.getServletContext().getRealPath("/upload");
//                        String serverFile = path+"/"+fileName;
//                        try {
//                                logger.info(path);
//                                if(!new File(path).exists()){
//                                        new File(path).mkdirs();
//                                    }
//                                if(!new File(serverFile).exists()){
//                                        new File(serverFile).createNewFile();
//                                    }
//                                byte[] bytes = file.getBytes();
//                                BufferedOutputStream stream =
//                                                new BufferedOutputStream(new FileOutputStream(new File(serverFile)));
//                                stream.write(bytes);
//                                stream.close();
//                } catch (Exception e) {
//                               e.printStackTrace();
//                            }
//                        product.setUrl("/upload/" + fileName);
//                    }
//                product.setInputUser(UserUtil.getUserFromSession(session));
//                productService.save(product);
//               model.setViewName("redirect:/admin/product");
        return model;
    }

    /**
     *
     * 查看商品
     *
     */
      @RequestMapping(value = "/{id}")
      public String showInfo(@PathVariable Integer id, Model model){
          Product product = productService.findById(id);
          model.addAttribute("product",product);
          return "admin/product/productCheck";
      }

//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonResult delete(@PathVariable Integer id){
//        productService.deleteProduct(id);
//        JsonResult result = new JsonResult();
//        result.setToSuccess();
//        return result;
//    }

    /**
     *
     * 删除商品
     *
     */
    @RequestMapping(value = "productAdmin/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult productDelete(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        logger.debug("商品删除成功...");

        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }




    private void uploadImage(Product product, HttpSession session, MultipartFile file) {
        String fileName = generateFileName();
        String path = generateFilePath(session);
        String serverFile = path + "/" + fileName;
        Picture picture = uploadAndSaveImg(session, file, fileName, path, serverFile);
        product.setMasterPic(picture);
    }

    private String generateFilePath(HttpSession session) {
        return session.getServletContext().getRealPath("/upload");
    }

    private String generateFileName() {
        return new Date().getTime() + ".jpg";
    }

    private Picture uploadAndSaveImg(HttpSession session, MultipartFile file, String fileName, String path, String serverFile) {
        Picture picture = new Picture();
        try {
            logger.info(path);
            if (!new File(path).exists()) {
                new File(path).mkdirs();
            }
            if (!new File(serverFile).exists()) {
                new File(serverFile).createNewFile();
            }
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(serverFile)));
            stream.write(bytes);
            stream.close();
            //缩放处理
            Image image = new Image(serverFile);
            image.resize(Constants.IMG_WIDTH,Constants.IMG_HEIGHT);
            image.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        picture.setMemo("商品上传");
        picture.setTitle("商品上传");
        picture.setUpdateTime(new Date());
        picture.setUrl("/upload/" + fileName);
        picture.setUpdateAdmin(AdminUtil.getAdminFromSession(session));
        pictureService.save(picture);
        return picture;
    }
}
