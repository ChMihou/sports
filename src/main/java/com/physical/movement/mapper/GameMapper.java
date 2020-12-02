package com.physical.movement.mapper;

import com.physical.movement.entity.Game;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper extends BaseMapper<Game> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKeyWithBLOBs(Game record);

    int updateByPrimaryKey(Game record);
}