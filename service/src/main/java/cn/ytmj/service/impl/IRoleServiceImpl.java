package cn.ytmj.service.impl;

import cn.ytmj.dao.IRoLeDao;
import cn.ytmj.domain.Permission;
import cn.ytmj.domain.Role;
import cn.ytmj.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 22:44
 */
@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoLeDao roLeDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roLeDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roLeDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roLeDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception {
        return roLeDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String str:ids
             ) {
            roLeDao.addPermissionToRole(roleId,str);
        }

    }
}
