package cn.ytmj.controller;

import cn.ytmj.domain.Orders;
import cn.ytmj.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

/*    //查询所有订单，未分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Orders> all = iOrderService.findAll();
        mv.addObject("ordersList", all);
        mv.setViewName("orders-list");
        System.out.println("all" + all);
        return mv;
    }*/

    //查询所有订单，分页
    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "pageSize", defaultValue = "5", required = true) int PageSize) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Orders> all = iOrderService.findAll(page, PageSize);
        //分页器
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("ordersList", pageInfo);
        mv.setViewName("orders-page-list");
        System.out.println("all" + all);
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) {

        ModelAndView mv = new ModelAndView();
        Orders orders = iOrderService.findById(id);
        System.out.println(orders+"-----------------------------------");
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
