package cn.ytmj.service.impl;

import cn.ytmj.dao.IPermissionDao;
import cn.ytmj.domain.Permission;
import cn.ytmj.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 23:25
 */
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

}
