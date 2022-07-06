package cn.asilentboy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebInfo {

    private String avatar;

    private String slogan;

    private String name;

    private String domain;

    private String notice;

    private String desc;
}
