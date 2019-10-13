package cn.ytmj.dao;

import cn.ytmj.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 16:27
 * private String id;
 * private String roleName;
 * private String roleDesc;
 * private List<Permission> permissions;
 * private List<User> users;
 */
public interface IRoLeDao {
    @Select("select * from role where id in(select roleid from users_role where userid=#{id})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "cn.ytmj.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleById(String id) throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert ")
    void save(Role role) throws Exception;
}
