package cn.ytmj.controller;

import cn.ytmj.domain.Orders;
import cn.ytmj.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.lang.Integer;
import java.util.List;

/**
 * @author rui
 * @create 2019-10-12 15:40
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrdersService iOrderService;



    //查询所有订单，分页
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = iOrderService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("ordersList", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {

        ModelAndView mv = new ModelAndView();
        Orders orders = iOrderService.findById(id);
        System.out.println(orders + "-----------------------------------");
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
