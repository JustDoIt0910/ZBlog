package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.dto.ArticleDTO;
import cn.asilentboy.blog.dto.ArticleListDTO;
import cn.asilentboy.blog.dto.ArticleListParam;
import cn.asilentboy.blog.mapper.ArticleMapper;
import cn.asilentboy.blog.pojo.Article;
import cn.asilentboy.blog.service.IArticleService;
import cn.asilentboy.blog.utils.http.ECode;
import cn.asilentboy.blog.utils.http.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    ArticleMapper mapper;

    @Override
    public Response getPostList(ArticleListParam param) {
        if(param.getSearchText() == null) {
            Page<Article> page = new Page<>(0, param.getPageSize());
            QueryWrapper<Article> query = new QueryWrapper<Article>()
                    .select("banner", "isTop", "pubTime", "title", "summary", "viewsCount", "commentsCount")
                    .eq("cid", param.getCid());
            Page<Article> result = mapper.selectPage(page, query);

            //使用MP的convert方法进行entity到dto的转换
            Page<ArticleListDTO> resDto = (Page<ArticleListDTO>)result.convert(a -> {
                ArticleListDTO dto = new ArticleListDTO();
                BeanUtils.copyProperties(a, dto);
                return dto;
            });
            return Response.makeSuccess(resDto.getRecords());
        }
        else {
            return Response.makeSuccess(null);
        }
    }

    @Override
    public Response getPost(int id) {
        Article article = mapper.selectById(id);
        if(article == null) {
            return new Response(ECode.POST_NOT_FOUND.getCode(),
                    ECode.POST_NOT_FOUND.getMessage());
        }
        else {
            ArticleDTO dto = new ArticleDTO();
            BeanUtils.copyProperties(article, dto);
            return Response.makeSuccess(dto);
        }
    }
}
