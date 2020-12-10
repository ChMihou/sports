package com.physical.movement.service;

import com.physical.movement.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> selectAll(Game game, int pageNum, int pageSize);

    Game select(Game game);

    Boolean insert(Game game);

    int updateByPrimaryKeySelective(Game game);

    int deleteByPrimaryKey(Integer id);
}
