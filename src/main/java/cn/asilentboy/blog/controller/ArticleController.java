package cn.asilentboy.blog.controller;


import cn.asilentboy.blog.dto.ArticleListParam;
import cn.asilentboy.blog.service.IArticleService;
import cn.asilentboy.blog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@RestController
@RequestMapping("/post")
public class ArticleController {

    @Autowired
    private IArticleService service;

    @GetMapping("/list")
    public Response getPostList(ArticleListParam param) {
        return service.getPostList(param);
    }

    @GetMapping("/{id}")
    public Response getPost(@PathVariable("id") Integer id) {
        return service.getPost(id);
    }
}
