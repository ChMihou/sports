package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Team;
import com.physical.movement.mapper.TeamMapper;
import com.physical.movement.service.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Resource
    TeamMapper teamMapper;

    @Override
    public List<Team> selectAll(Team team, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return teamMapper.selectAll(team);
    }

    @Override
    public Team select(Team team) {
        return teamMapper.select(team);
    }

    @Override
    public Boolean insert(Team team) {
        return teamMapper.insert(team);
    }

    @Override
    public int updateByPrimaryKeySelective(Team team) {
        return teamMapper.updateByPrimaryKeySelective(team);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return teamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int selectCount() {
        return teamMapper.selectCount();
    }
}
