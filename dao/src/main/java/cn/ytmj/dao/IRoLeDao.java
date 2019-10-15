package cn.ytmj.dao;


import cn.ytmj.domain.Permission;
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

    @Insert("insert into role(rolename,roledesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{id}")
    Role findById(String id) throws Exception;


    @Select("select * from permission where id not in (select permissionid from role_permission where roleid=#{id})")
    List<Permission> findOtherPermissions(String id) throws Exception;

    @Insert("insert into role_permission(roleId,permissionid) values (#{roleId},#{str})")
    void addPermissionToRole(@Param("roleId") String userId, @Param("str") String str) throws Exception;
}
