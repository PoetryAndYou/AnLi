package cn.ytmj.service;

import cn.ytmj.domain.Orders;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-12 15:47
 */
public interface IOrdersService {
    public List<Orders> findAll(int page, int pageSize) throws Exception;

    Orders findById(String id) throws Exception;
}
