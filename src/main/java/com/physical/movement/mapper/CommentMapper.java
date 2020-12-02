package com.physical.movement.mapper;

import com.physical.movement.entity.Comment;

public interface CommentMapper extends BaseMapper<Comment> {

    int insertSelective(Comment record);
}