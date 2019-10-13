package cn.ytmj.dao;

import cn.ytmj.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 21:53
 */
public interface IPermissionDao {

    @Select("SELECT * from Permission where id in(SELECT permissionid from role_permission where roleid=#{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;
}
