package cn.asilentboy.blog.mapper;

import cn.asilentboy.blog.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select id from category where name = #{name}")
    Integer getId(@Param("name") String name);
}
