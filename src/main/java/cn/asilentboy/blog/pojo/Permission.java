package cn.asilentboy.blog.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission implements Serializable {

    @TableId
    private Long id;

    private String menuName;
    /**
     * 路由地址
     */
    private String path;

    /**
     * 权限状态（0正常 1停用）
     */
    private String status;
    /**
     * 权限标识
     */
    private String perms;

    private Date createTime;

    private Date updateTime;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private Integer delFlag;
}
