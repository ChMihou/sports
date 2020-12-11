package com.physical.movement.service;


import com.physical.movement.entity.UserTeamRef;
import com.physical.movement.entity.vo.UserTeamVo;

import java.util.List;

public interface UserTeamRefService {
    List<UserTeamRef> selectAll(UserTeamRef UserTeamRef, int pageNum, int pageSize);

    UserTeamRef select(UserTeamRef UserTeamRef);

    Boolean insert(UserTeamRef UserTeamRef);

    int updateByPrimaryKeySelective(UserTeamRef UserTeamRef);

    int deleteByPrimaryKey(Integer id);

    List<UserTeamVo> selectUserTeam(UserTeamVo userTeamVo);
}
