package cn.asilentboy.blog.controller;

import cn.asilentboy.blog.pojo.User;
import cn.asilentboy.blog.service.ILoginService;
import cn.asilentboy.blog.utils.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private ILoginService service;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        return service.login(user);
    }
}
