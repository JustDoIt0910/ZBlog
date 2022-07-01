package cn.asilentboy.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTO {
    private Date pubTime;
    private String title;
    private String content;
    private Integer viewsCount;
    private Integer commentsCount;
}
