package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.UserTeamRef;
import com.physical.movement.entity.vo.UserTeamVo;
import com.physical.movement.mapper.UserTeamRefMapper;
import com.physical.movement.service.UserTeamRefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserTeamRefServiceImpl implements UserTeamRefService {

    @Resource
    UserTeamRefMapper userTeamRefMapper;

    @Override
    public List<UserTeamRef> selectAll(UserTeamRef UserTeamRef, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userTeamRefMapper.selectAll(UserTeamRef);
    }

    @Override
    public UserTeamRef select(UserTeamRef UserTeamRef) {
        return userTeamRefMapper.select(UserTeamRef);
    }

    @Override
    public Boolean insert(UserTeamRef UserTeamRef) {
        return userTeamRefMapper.insert(UserTeamRef);
    }

    @Override
    public int updateByPrimaryKeySelective(UserTeamRef UserTeamRef) {
        return userTeamRefMapper.updateByPrimaryKeySelective(UserTeamRef);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userTeamRefMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserTeamVo> selectUserTeam(UserTeamRef userTeamRef, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userTeamRefMapper.selectUserTeam(userTeamRef);
    }
}
