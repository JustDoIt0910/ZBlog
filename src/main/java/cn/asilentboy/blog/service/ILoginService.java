package cn.asilentboy.blog.service;

import cn.asilentboy.blog.pojo.User;
import cn.asilentboy.blog.utils.http.Response;

public interface ILoginService {
    Response login(User user);
}
