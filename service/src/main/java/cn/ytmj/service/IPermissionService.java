package cn.ytmj.service;

import cn.ytmj.domain.Permission;
import cn.ytmj.domain.Product;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 23:25
 */
public interface IPermissionService {
    public List<Permission> findAll() throws Exception;

}
