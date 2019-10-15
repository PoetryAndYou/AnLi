package cn.ytmj.service.impl;

import cn.ytmj.dao.IUserDao;
import cn.ytmj.domain.Role;
import cn.ytmj.domain.UserInfo;
import cn.ytmj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author rui
 * @create 2019-10-12 23:55
 */
@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        //处理自己的用户对象UserDetails
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
////        User(String username, String password, boolean enabled,
//			boolean accountNonExpired, boolean credentialsNonExpired,
//			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));

        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role r : roles
        ) {
            list.add(new SimpleGrantedAuthority("ROLE_" + r.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //加密
        String encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findRoleByUserId(String id) throws Exception {
        return userDao.findRoleByUserId(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String str : ids
        ) {
            userDao.addRoleToUser(userId,str);
        }
    }
}
