package cn.ytmj.dao;


import cn.ytmj.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-11 16:00
 */

public interface IProductDao {


    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into PRODUCT ( productnum, productname, cityname, departuretime, productprice, productdesc, productstatus) values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
    //根据id查询product
    @Select("select * from product where id=#{ordersId}")
    Product findById(String ordersId);
}
