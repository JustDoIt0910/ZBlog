package cn.asilentboy.blog.controller;

import cn.asilentboy.blog.pojo.Social;
import cn.asilentboy.blog.pojo.WebInfo;
import cn.asilentboy.blog.utils.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private Environment env;

    @GetMapping("/site")
    public Response getWebInfo() {
        WebInfo info = new WebInfo(
                env.getProperty("webinfo.avatar"),
                env.getProperty("webinfo.slogan"),
                env.getProperty("webinfo.name"),
                env.getProperty("webinfo.domain"),
                env.getProperty("webinfo.notice"),
                env.getProperty("webinfo.desc"));
        return Response.makeSuccess(info);
    }

    @GetMapping("/social")
    public Response getSocial() {
        List<Social> socialList = Arrays.asList(
                new Social("GitHub", "icongithub", "", "https://github.com/JustDoIt0910"),
                new Social("QQ", "iconqq", "#1AB6FF", "http://wpa.qq.com/msgrd?v=3&uin=1396812324&site=qq&menu=yes")
        );
        return Response.makeSuccess(socialList);
    }
}
