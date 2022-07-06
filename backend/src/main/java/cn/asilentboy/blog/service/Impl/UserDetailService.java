package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.mapper.PermissionsMapper;
import cn.asilentboy.blog.mapper.UserMapper;
import cn.asilentboy.blog.pojo.MyUserDetail;
import cn.asilentboy.blog.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<User>();
        query.eq(User::getUsername, username);
        User user = userMapper.selectOne(query);
        if(Objects.isNull(user))
            throw new UsernameNotFoundException("用户不存在");

        List<String> perms = permissionsMapper.getPermsByUserId(user.getId());
        return new MyUserDetail(user, perms);
    }
}
