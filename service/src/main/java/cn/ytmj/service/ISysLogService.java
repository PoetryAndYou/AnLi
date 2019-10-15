package cn.ytmj.service;

import cn.ytmj.domain.SysLog;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-14 21:38
 */
public interface ISysLogService {
    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll()throws Exception;
}
