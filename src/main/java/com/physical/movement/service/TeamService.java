package com.physical.movement.service;

import com.physical.movement.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> selectAll(Team Team, int pageNum, int pageSize);

    Team select(Team team);

    Boolean insert(Team team);

    int updateByPrimaryKeySelective(Team team);

    int deleteByPrimaryKey(Integer id);

    int selectCount();

}
