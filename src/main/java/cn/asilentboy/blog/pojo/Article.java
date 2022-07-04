package cn.asilentboy.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章所属分类id
     */
    private Integer cid;

    /**
     * 配图
     */
    private String banner;

    /**
     * 是否置顶
     */
    @TableField("isTop")
    private Boolean isTop;

    /**
     * 发布日期
     */
    @TableField("pubTime")
    private Date pubTime;

    private String title;

    private String summary;

    private String content;

    @TableField("viewsCount")
    private Integer viewsCount;

    @TableField("commentsCount")
    private Integer commentsCount;
}
