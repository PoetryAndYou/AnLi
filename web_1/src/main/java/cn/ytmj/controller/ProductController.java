package cn.ytmj.controller;


import cn.ytmj.domain.Product;
import cn.ytmj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author rui
 * @create 2019-10-11 16:56
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/save.do")
    public void sava(Product product, HttpServletRequest request, HttpServletResponse response) throws Exception {
        productService.sava(product);
        response.sendRedirect(request.getContextPath() + "/product/findAll.do");
    }

    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> all = productService.findAll();
        mv.addObject("productList", all);
        mv.setViewName("product-list");
        System.out.println("all" + all);
        return mv;
    }
/*
    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(name="id" ,required = true)String ordersId){
        ModelAndView mv = new ModelAndView();
        productService.findById(ordersId);

    }
*/


}
