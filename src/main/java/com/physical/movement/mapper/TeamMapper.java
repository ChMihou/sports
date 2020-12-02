package com.physical.movement.mapper;

import com.physical.movement.entity.Team;

public interface TeamMapper extends BaseMapper<Team> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
}