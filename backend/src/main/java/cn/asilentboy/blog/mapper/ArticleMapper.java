package cn.asilentboy.blog.mapper;

import cn.asilentboy.blog.dto.ArticleDTO;
import cn.asilentboy.blog.dto.ArticleListDTO;
import cn.asilentboy.blog.dto.ArticlePostData;
import cn.asilentboy.blog.pojo.Article;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select article.id, article.banner, article.isTop, article.pubTime, " +
            "article.title, article.summary, article.viewsCount, " +
            "article.commentsCount, category.name as categoryName " +
            "from article left join category on article.cid = category.id " +
            "${ew.customSqlSegment} " +
            "order by pubTime desc")
    IPage<ArticleListDTO> getArticleList(@Param("page") IPage<ArticleListDTO> page, @Param("ew") Wrapper<Article> queryWrapper);

    @Select("select article.title, article.pubTime, article.summary, article.content, " +
            "article.viewsCount, article.commentsCount, category.name as categoryName, " +
            "article.id, article.cid, article.banner " +
            "from article left join category on article.cid = category.id " +
            "where article.id = #{id}")
    ArticleDTO getArticleDetail(@Param("id") Integer id);

    @Insert("<script>" +
            "insert into article (cid, banner, pubTime, title, summary, content) " +
            "values(" +
            "<if test='data.cid != null'>" +
                "#{data.cid}, " +
            "</if>" +
            "<if test='data.cid == null'>" +
                "0, " +
            "</if>" +
            "#{data.banner}, " +
            "now(), " +
            "#{data.title}, " +
            "#{data.summary}, " +
            "#{data.content})" +
            "</script>")
    void addArticle(@Param("data") ArticlePostData data);

    @Delete({"delete from article where id = #{id}"})
    void deleteArticle(@Param("id") Integer id);

    @Update("<script>" +
            "update article set " +
            "<foreach collection='data' item='item' index='index' separator=','>" +
                "<if test='item != null'>" +
                    "${index} = #{item}" +
                "</if>" +
            "</foreach> " +
            "where id = #{id}" +
            "</script>")
    void updateArticle(@Param("id") Integer id,
                       @Param("data") Map<String, Object> data);
}
