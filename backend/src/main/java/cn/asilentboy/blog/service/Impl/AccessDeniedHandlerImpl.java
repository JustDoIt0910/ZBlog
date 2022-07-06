package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.utils.http.ECode;
import cn.asilentboy.blog.utils.http.Response;
import cn.asilentboy.blog.utils.http.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Response resp = new Response(ECode.FORBIDDEN.getCode(), ECode.FORBIDDEN.getMessage());
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(resp);
        WebUtil.renderString(response, json);
    }
}