package cn.asilentboy.blog.controller;


import cn.asilentboy.blog.dto.ArticleListParam;
import cn.asilentboy.blog.service.IArticleService;
import cn.asilentboy.blog.utils.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('article:add')")
    public Response addPost() {
        return null;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('article:edit')")
    public Response editPost(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping("/list")
    public Response getPostList(ArticleListParam param) {
        return service.getPostList(param);
    }

    @GetMapping("/{id}")
    public Response getPost(@PathVariable("id") Integer id) {
        return service.getPost(id);
    }
}
