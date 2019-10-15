package cn.ytmj.controller;

import cn.ytmj.domain.Role;
import cn.ytmj.domain.UserInfo;
import cn.ytmj.service.IUserService;
import org.apache.ibatis.annotations.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole(authentication.principal.username=='345')")
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

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据id查用户

        UserInfo userInfo = userService.findById(id);
        //根据id查询用户可以添加的角色
        List<Role> roles = userService.findRoleByUserId(id);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    //添加角色
    @RequestMapping("/addRoleToUser.do")
    public void addRoleToUser(HttpServletResponse response, HttpServletRequest request, @RequestParam(name = "userId", required = true) String userId, @RequestParam( name = "ids",required = true) String[] ids) throws Exception {
        userService.addRoleToUser(userId,ids);
        response.sendRedirect(request.getContextPath() + "/user/findAll.do");
    }


}
