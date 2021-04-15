package com.physical.movement.mapper;

import com.physical.movement.entity.Comment;
import com.physical.movement.entity.vo.UserCommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    int deleteListId(int[] ids);

    List<UserCommentVo> selectAllCommentUser(Comment comment);
}