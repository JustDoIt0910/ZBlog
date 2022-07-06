package cn.asilentboy.blog.dto;

import lombok.Data;

@Data
public class ArticlePostData {
    private String title;

    private Integer cid;

    private String summary;

    private String content;

    private String banner;
}
