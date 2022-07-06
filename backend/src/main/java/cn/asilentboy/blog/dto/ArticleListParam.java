package cn.asilentboy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListParam {
    //所属标签
    String cate;
    //分页大小
    int pageSize;
    //第几页
    int currentPage;
    //搜索
    String searchText;
}
