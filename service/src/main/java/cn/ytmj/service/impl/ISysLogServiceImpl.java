package cn.ytmj.service.impl;

import cn.ytmj.dao.ISysLogDao;
import cn.ytmj.domain.SysLog;
import cn.ytmj.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-14 21:39
 */
@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll()throws Exception {
        return sysLogDao.findAll();
    }
}
