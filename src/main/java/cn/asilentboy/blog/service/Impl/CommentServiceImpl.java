package cn.asilentboy.blog.service.Impl;

import cn.asilentboy.blog.mapper.CommentMapper;
import cn.asilentboy.blog.pojo.Comment;
import cn.asilentboy.blog.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
