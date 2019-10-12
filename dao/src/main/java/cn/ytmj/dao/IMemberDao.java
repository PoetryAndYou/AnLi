package cn.ytmj.dao;

import cn.ytmj.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author rui
 * @create 2019-10-12 19:07
 */
public interface IMemberDao {
    @Select("SELECT * from member where id=#{id}")
    public Member findById(String id) throws Exception;

}
