package com.physical.movement.mapper;

import com.physical.movement.entity.Game;

public interface GameMapper extends BaseMapper<Game>{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
}