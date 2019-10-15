package cn.ytmj.controller;

import cn.ytmj.domain.Permission;
import cn.ytmj.domain.Role;
import cn.ytmj.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 22:39
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public void save(Role role, HttpServletRequest request, HttpServletResponse response) throws Exception {

        roleService.save(role);

        response.sendRedirect(request.getContextPath() + "/role/findAll.do");
    }


    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);

        List<Permission> permission = roleService.findOtherPermissions(id);
        mv.addObject("permissionList", permission);
        mv.addObject("role", role);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    public void addPermissionToRole(HttpServletResponse response, HttpServletRequest request, @RequestParam(name = "roleId", required = true) String roleId, @RequestParam( name = "ids",required = true) String[] ids) throws Exception {
        roleService.addPermissionToRole(roleId,ids);
        response.sendRedirect(request.getContextPath() + "/role/findAll.do");
    }

}
