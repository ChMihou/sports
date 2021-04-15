package com.physical.movement.service;

import com.physical.movement.entity.Comment;
import com.physical.movement.entity.vo.UserCommentVo;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    Comment select(Comment comment);

    Boolean insert(Comment comment);

    List<Comment> selectAll(Comment comment, int pageNum, int pageSize);

    int deleteListId(int[] ids);

    int selectCount();

    List<UserCommentVo> selectAllCommentUser(Comment comment, int pageNum, int pageSize);
}

