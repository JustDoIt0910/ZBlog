package cn.asilentboy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListParam {
    int cid;
    int pageSize;
    String searchText;
}
