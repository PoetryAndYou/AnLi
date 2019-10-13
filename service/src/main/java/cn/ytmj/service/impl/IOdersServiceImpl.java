package cn.ytmj.service.impl;

import cn.ytmj.dao.IOrdersDao;
import cn.ytmj.domain.Orders;
import cn.ytmj.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-12 15:48
 */
@Service
@Transactional
public class IOdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return iOrdersDao.findById(id);
    }
}
