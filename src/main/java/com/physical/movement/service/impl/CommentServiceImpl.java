package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Comment;
import com.physical.movement.entity.vo.UserCommentVo;
import com.physical.movement.mapper.CommentMapper;
import com.physical.movement.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public Comment select(Comment comment) {
        return commentMapper.select(comment);
    }

    @Override
    public Boolean insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public List<Comment> selectAll(Comment comment, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return commentMapper.selectAll(comment);
    }

    @Override
    public int deleteListId(int[] ids) {
        return commentMapper.deleteListId(ids);
    }

    @Override
    public int selectCount() {
        return commentMapper.selectCount();
    }

    @Override
    public List<UserCommentVo> selectAllCommentUser(Comment comment, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return commentMapper.selectAllCommentUser(comment);
    }
}
