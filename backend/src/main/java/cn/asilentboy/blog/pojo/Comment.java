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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论所属文章id
     */
    @TableField("postId")
    private Integer postId;

    /**
     * 父级评论id
     */
    @TableField("parentId")
    private Integer parentId;

    @TableField("fromUserId")
    private Integer fromUserId;

    @TableField("toUserId")
    private Integer toUserId;

    private String content;

    @TableField("createTime")
    private Date createTime;


}
