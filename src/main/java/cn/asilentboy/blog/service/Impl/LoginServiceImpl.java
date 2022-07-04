package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.pojo.MyUserDetail;
import cn.asilentboy.blog.pojo.User;
import cn.asilentboy.blog.service.ILoginService;
import cn.asilentboy.blog.utils.cache.RedisCache;
import cn.asilentboy.blog.utils.http.Response;
import cn.asilentboy.blog.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RedisCache cache;

    @Override
    public Response login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );
        Authentication auth = manager.authenticate(authenticationToken);
        if(Objects.isNull(auth))
            throw new RuntimeException("登录失败");
        MyUserDetail userDetail = (MyUserDetail) auth.getPrincipal();
        String userId = userDetail.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        cache.setCacheObject("zblog:login:" + userId, userDetail);
        return Response.makeSuccess(jwt);
    }
}
