package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.dto.ArticleDTO;
import cn.asilentboy.blog.dto.ArticleListDTO;
import cn.asilentboy.blog.dto.ArticleListParam;
import cn.asilentboy.blog.dto.ArticlePostData;
import cn.asilentboy.blog.mapper.ArticleMapper;
import cn.asilentboy.blog.mapper.CategoryMapper;
import cn.asilentboy.blog.pojo.Article;
import cn.asilentboy.blog.service.IArticleService;
import cn.asilentboy.blog.utils.http.ECode;
import cn.asilentboy.blog.utils.http.Response;
import cn.asilentboy.blog.utils.oss.OssUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

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
    ArticleMapper articleMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    OssUtil oss;

    @Override
    public Response getPostList(ArticleListParam param) {
        IPage<ArticleListDTO> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        QueryWrapper<Article> query = new QueryWrapper<>();
        if(!Strings.isEmpty(param.getCate())) {
            Integer cate = categoryMapper.getId(param.getCate());
            int cid = cate != null ? cate : -1;
            query = query.eq("cid", cid);
        }
        if(!Strings.isEmpty(param.getSearchText()))
            query = query.like("title", param.getSearchText());
        IPage<ArticleListDTO> result = articleMapper.getArticleList(page, query);

        //使用MP的convert方法进行entity到dto的转换
//        Page<ArticleListDTO> resDto = (Page<ArticleListDTO>)result.convert(a -> {
//            ArticleListDTO dto = new ArticleListDTO();
//            BeanUtils.copyProperties(a, dto);
//            return dto;
//        });
        return Response.makeSuccess(result.getRecords());
    }

    @Override
    public Response getPost(int id) {
        ArticleDTO article = articleMapper.getArticleDetail(id);
        if(article == null) {
            return new Response(ECode.POST_NOT_FOUND.getCode(),
                    ECode.POST_NOT_FOUND.getMessage());
        }
        else
            return Response.makeSuccess(article);
    }

    @Override
    public Response addPost(ArticlePostData data) {
        articleMapper.addArticle(data);
        return Response.makeSuccess(data);
    }

    @Override
    public Response deletePost(Integer id) {
        articleMapper.deleteArticle(id);
        return Response.makeSuccess(id);
    }

    @Override
    public Response updateArticle(Integer id, ArticlePostData data) {
        Map<String, Object> dataMap = beanToMap(data);
        articleMapper.updateArticle(id, dataMap);
        return Response.makeSuccess(data);
    }

    @Override
    public Response upload(MultipartFile file, String path) {
        try {
            String fileName = file.getOriginalFilename();
            String pathDecoded = URLDecoder.decode(path, "UTF-8");
            String key = pathDecoded + "/" + fileName;
            byte[] data = file.getBytes();
            String publicUrl = oss.upload(key, data);
            return Response.makeSuccess(publicUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.makeError(e.getMessage());
        }
    }

    private static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = bean.getClass().getDeclaredFields();
        try {
            for(Field f: fields) {
                f.setAccessible(true);
                map.put(f.getName(), f.get(bean));
            }
        } catch (IllegalAccessException ignored) {
            return null;
        }
        return map;
    }
}
