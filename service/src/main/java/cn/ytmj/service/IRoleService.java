package cn.ytmj.service;

import cn.ytmj.domain.Permission;
import cn.ytmj.domain.Role;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 22:44
 */
public interface IRoleService {
    public List<Role> findAll() throws Exception;

    void save(Role role)throws Exception;

    Role findById(String id)throws Exception;

    List<Permission> findOtherPermissions(String id)throws Exception;

    void addPermissionToRole(String roleId, String[] ids)throws Exception;
}
