package cn.asilentboy.blog.service;

import cn.asilentboy.blog.pojo.Category;
import cn.asilentboy.blog.utils.http.Response;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
public interface ICategoryService extends IService<Category> {

    Response getCategory();
}
