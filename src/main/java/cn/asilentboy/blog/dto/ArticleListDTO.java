package cn.asilentboy.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleListDTO {

    private Integer id;

    private String banner;

    private Boolean isTop;

    private String pubTime;

    private String title;

    private String summary;

    private Integer viewsCount;

    private Integer commentsCount;

    private String categoryName;
}
