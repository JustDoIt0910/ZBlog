package cn.asilentboy.blog.controller;


import cn.asilentboy.blog.service.ICategoryService;
import cn.asilentboy.blog.utils.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@RestController
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @GetMapping("/category")
    public Response getCategory() {
        return service.getCategory();
    }
}
