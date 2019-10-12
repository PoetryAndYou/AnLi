package cn.ytmj.dao;

import cn.ytmj.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-12 19:18
 */
public interface ITravellerDao {
    @Select("select * from order_traveller,traveller where order_traveller.orderid = #{id}")
    List<Traveller> findByOrdersId(String id);


}
