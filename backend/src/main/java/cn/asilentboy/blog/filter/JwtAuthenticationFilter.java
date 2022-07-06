package cn.asilentboy.blog.filter;

import cn.asilentboy.blog.pojo.MyUserDetail;
import cn.asilentboy.blog.utils.cache.RedisCache;
import cn.asilentboy.blog.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache cache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)) {
                filterChain.doFilter(request, response);
                return;
        }
        if(token.startsWith("Bearer ")) {
            try {
                Claims claims = JwtUtil.parseJWT(token.substring(7));
                String userId = claims.getSubject();
                String redisKey = "zblog:login:" + userId;
                MyUserDetail userDetail = cache.getCacheObject(redisKey);
                if(Objects.isNull(userDetail))
                    throw new RuntimeException("用户未登录");
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException("token非法");
            }
        }
        else
            throw new RuntimeException("token非法");
    }
}
