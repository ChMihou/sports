package com.physical.movement.mapper;

import com.physical.movement.entity.SysUser;

import java.util.List;

public interface SysuserMapper extends BaseMapper<SysUser> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> selectByUser(SysUser sysUser);
}