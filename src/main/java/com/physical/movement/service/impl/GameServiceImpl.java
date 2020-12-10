package com.physical.movement.service.impl;

import com.physical.movement.entity.Game;
import com.physical.movement.mapper.GameMapper;
import com.physical.movement.service.GameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Resource
    GameMapper gameMapper;

    @Override
    public List<Game> selectAll(Game game, int pageNum, int pageSize) {
        return gameMapper.selectAll(game);
    }

    @Override
    public Game select(Game game) {
        return gameMapper.select(game);
    }

    @Override
    public Boolean insert(Game game) {
        return gameMapper.insert(game);
    }

    @Override
    public int updateByPrimaryKeySelective(Game game) {
        return gameMapper.updateByPrimaryKeySelective(game);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return gameMapper.deleteByPrimaryKey(id);
    }
}
