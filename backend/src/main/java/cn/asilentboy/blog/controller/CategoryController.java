package cn.asilentboy.blog.controller;


import cn.asilentboy.blog.pojo.Category;
import cn.asilentboy.blog.service.ICategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @GetMapping("/list")
    public Response getCategory() {
        return service.getCategory();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('category:add')")
    public Response addCategory(@RequestBody Category cate) {
        return service.addCategory(cate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('category:delete')")
    public Response deleteCategory(@PathVariable("id") Integer id) {
        return service.deleteCategory(id);
    }
}
