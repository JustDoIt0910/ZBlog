package cn.asilentboy.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleListDTO {
    private String banner;
    private Boolean isTop;
    private Date pubTime;
    private String title;
    private String summary;
    private String content;
    private Integer viewsCount;
    private Integer commentsCount;
}
