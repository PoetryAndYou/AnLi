package cn.ytmj.service;

import cn.ytmj.domain.Product;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-11 16:22
 */
public interface IProductService {
    public List<Product> findAll() throws Exception;

    public void sava(Product product)throws Exception ;

    void findById(String ordersId);
}
