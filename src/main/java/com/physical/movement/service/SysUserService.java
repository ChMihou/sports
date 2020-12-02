package com.physical.movement.service;

import com.physical.movement.entity.SysUser;

public interface SysUserService {

    SysUser select(SysUser sysUser);

    Boolean insert(SysUser sysUser);

    int updateByPrimaryKeySelective(SysUser record);
}
