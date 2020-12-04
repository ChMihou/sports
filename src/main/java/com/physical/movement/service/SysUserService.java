package com.physical.movement.service;

import com.physical.movement.entity.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser select(SysUser sysUser);

    Boolean insert(SysUser sysUser);

    int updateByPrimaryKeySelective(SysUser record);

    List<SysUser> selectAll(SysUser sysUser,int pageNum,int pageSize);

    int deleteByPrimaryKey(Integer id);


}
