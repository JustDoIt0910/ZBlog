package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.utils.http.ECode;
import cn.asilentboy.blog.utils.http.Response;
import cn.asilentboy.blog.utils.http.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Response resp = new Response(
                ECode.UNAUTHORIZED.getCode(),
                ECode.UNAUTHORIZED.getMessage()
        );
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(resp);
        WebUtil.renderString(response, json);
    }
}
