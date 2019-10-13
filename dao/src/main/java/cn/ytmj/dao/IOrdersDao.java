package cn.ytmj.dao;

import cn.ytmj.domain.Member;
import cn.ytmj.domain.Orders;
import cn.ytmj.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-12 15:46
 */
public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.ytmj.dao.IProductDao.findById"))
    })

    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.ytmj.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "cn.ytmj.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class ,many = @Many(select = "cn.ytmj.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String id) throws Exception;
}
