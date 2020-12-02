package com.physical.movement.mapper;

import com.physical.movement.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    int insertSelective(Comment record);
}