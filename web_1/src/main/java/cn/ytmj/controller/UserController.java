package cn.ytmj.controller;

import cn.ytmj.domain.UserInfo;
import cn.ytmj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 19:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll();
        System.err.println(list.get(0).getUsername() + "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        mv.addObject("userList", list);
        mv.setViewName("user-list");
        return mv;

    }

    //用户添加
    @RequestMapping("save.do")
    public void save(UserInfo userInfo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        userService.save(userInfo);
        response.sendRedirect(request.getContextPath() + "/user/findAll.do");
    }

    //通过id查询用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

}
