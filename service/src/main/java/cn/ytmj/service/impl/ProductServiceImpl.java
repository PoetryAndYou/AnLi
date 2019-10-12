package cn.ytmj.service.impl;


import cn.ytmj.dao.IProductDao;
import cn.ytmj.domain.Product;
import cn.ytmj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-11 16:23
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public void sava(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void findById(String ordersId) {
        productDao.findById(ordersId);
    }
}
