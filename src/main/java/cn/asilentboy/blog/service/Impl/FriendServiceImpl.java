package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.mapper.FriendMapper;
import cn.asilentboy.blog.pojo.Friend;
import cn.asilentboy.blog.service.IFriendService;
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
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

}
