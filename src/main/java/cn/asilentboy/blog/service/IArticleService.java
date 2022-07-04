package cn.asilentboy.blog.service;

import cn.asilentboy.blog.dto.ArticleListParam;
import cn.asilentboy.blog.pojo.Article;
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
public interface IArticleService extends IService<Article> {

    Response getPostList(ArticleListParam param);
    Response getPost(int id);
}
