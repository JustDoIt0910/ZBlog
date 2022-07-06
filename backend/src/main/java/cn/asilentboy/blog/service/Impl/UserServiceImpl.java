package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.mapper.UserMapper;
import cn.asilentboy.blog.pojo.User;
import cn.asilentboy.blog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JustDoIt0910
 * @since 2022-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
