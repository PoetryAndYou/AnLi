package cn.ytmj.dao;

import cn.ytmj.domain.Role;
import cn.ytmj.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-13 13:24
 * private String id;
 * private String username;
 * private String email;
 * private String password;
 * private String phoneNum;
 * private int status;
 * private String statusStr;
 * private List<Role> roles;
 */
public interface IUserDao {
    @Select("select * from users where username=#{username} ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.ytmj.dao.IRoLeDao.findRoleById"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    //添加用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    //通过id查询用户信息
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.ytmj.dao.IRoLeDao.findRoleById"))
    })
    UserInfo findById(String id) throws Exception;

    @Select("select * from role where id not in (select roleid from users_role where userid=#{id})")
    List<Role> findRoleByUserId(String id) throws Exception;

    @Insert("insert into users_role(userid,roleid) values (#{userId},#{str})")
    void addRoleToUser(@Param("userId") String userId, @Param("str") String str) throws Exception;
}
