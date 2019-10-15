package cn.ytmj.dao;

import cn.ytmj.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-14 21:40
 * private String id;
 * private Date visitTime;
 * private String visitTimeStr;
 * private String username;
 * private String ip;
 * private String url;
 * private Long executionTime;
 * private String method;
 */
public interface ISysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    List<SysLog> findAll()throws Exception;
}
