package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.mapper.CategoryMapper;
import cn.asilentboy.blog.pojo.Category;
import cn.asilentboy.blog.service.ICategoryService;
import cn.asilentboy.blog.utils.http.Response;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper mapper;

    @Override
    public Response getCategory() {
        List<Category> categories = list();
        return Response.makeSuccess(categories);
    }

    @Override
    public Response addCategory(Category cate) {
        save(cate);
        return Response.makeSuccess(cate);
    }

    @Override
    public Response deleteCategory(Integer id) {
        mapper.deleteById(id);
        return Response.makeSuccess(id);
    }
}
