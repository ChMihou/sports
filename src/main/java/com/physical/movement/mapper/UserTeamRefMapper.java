package com.physical.movement.mapper;

import com.physical.movement.entity.UserTeamRef;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTeamRefMapper extends BaseMapper<UserTeamRef> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserTeamRef record);

    UserTeamRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTeamRef record);

    int updateByPrimaryKey(UserTeamRef record);
}