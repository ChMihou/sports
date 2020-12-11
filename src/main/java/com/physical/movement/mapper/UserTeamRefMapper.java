package com.physical.movement.mapper;

import com.physical.movement.entity.UserTeamRef;
import com.physical.movement.entity.vo.UserTeamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTeamRefMapper extends BaseMapper<UserTeamRef> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserTeamRef record);

    UserTeamVo selectByPrimaryKey(Integer id);

    List<UserTeamVo> selectUserTeam(UserTeamVo userTeamVo);

    int updateByPrimaryKeySelective(UserTeamRef record);

    int updateByPrimaryKey(UserTeamRef record);
}