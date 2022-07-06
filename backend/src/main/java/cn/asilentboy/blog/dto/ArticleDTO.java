package cn.asilentboy.blog.dto;

import lombok.Data;

@Data
public class ArticleDTO {

    private Integer id;

    private String banner;

    private Integer cid;

    private String pubTime;

    private String title;

    private String summary;

    private String content;

    private Integer viewsCount;

    private Integer commentsCount;

    private String categoryName;
}
