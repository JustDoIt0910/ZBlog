package cn.asilentboy.blog;


import cn.asilentboy.blog.mapper.PermissionsMapper;
import cn.asilentboy.blog.pojo.User;
import cn.asilentboy.blog.utils.cache.RedisCache;
import cn.asilentboy.blog.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RedisCache cache;

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Test
    void contextLoads() {
//        cache.setCacheObject("blog:test", "ok");
//        String value = cache.getCacheObject("blog:test");
//        System.out.println(value);
//        cache.deleteObject("blog:test");
//
//        User user = new User(1, "name", "123@qq.com", "");
//        cache.setCacheObject("blog:test:user", user);
//        User u = cache.getCacheObject("blog:test:user");
//        System.out.println(u);

//        List<String> perms = permissionsMapper.getPermsByUserId(1);
//        System.out.println(perms);

        String jwt = JwtUtil.createJWT("jwt subject");
        Claims claims = JwtUtil.parseJWT(jwt);
        System.out.println(claims.getSubject());
    }
}
