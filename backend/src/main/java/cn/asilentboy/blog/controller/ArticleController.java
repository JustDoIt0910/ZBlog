package cn.asilentboy.blog.controller;


import cn.asilentboy.blog.dto.ArticleListParam;
import cn.asilentboy.blog.dto.ArticlePostData;
import cn.asilentboy.blog.service.IArticleService;
import cn.asilentboy.blog.utils.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;

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
    public Response addPost(@RequestBody ArticlePostData data) {
        return service.addPost(data);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('article:delete')")
    public Response deletePost(@PathVariable("id") Integer id) {
        return service.deletePost(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('article:edit')")
    public Response editPost(@PathVariable("id") Integer id,
                             @RequestBody ArticlePostData data) {
        return service.updateArticle(id, data);
    }

    @GetMapping("/list")
    public Response getPostList(ArticleListParam param) {
        return service.getPostList(param);
    }

    @GetMapping("/{id}")
    public Response getPost(@PathVariable("id") Integer id) {
        return service.getPost(id);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('upload')")
    public Response upload(@RequestParam("file") MultipartFile file,
                           @RequestHeader("title") String title) {
        return service.upload(file, title);
    }
}
