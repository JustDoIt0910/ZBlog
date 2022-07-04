package cn.asilentboy.blog.mapper;

import cn.asilentboy.blog.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionsMapper extends BaseMapper<Permission> {

    @Select({"select distinct perms from " +
            "`user_role` ur left join `role` r on r.id = ur.role_id " +
            "left join `role_perm` rp on rp.role_id = ur.role_id " +
            "left join permission p on p.id = rp.perm_id " +
            "where user_id = #{userId}"})
    List<String> getPermsByUserId(@Param("userId") Integer userId);
}
