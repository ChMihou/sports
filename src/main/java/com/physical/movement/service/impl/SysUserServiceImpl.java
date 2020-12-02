package com.physical.movement.service.impl;

import com.physical.movement.entity.SysUser;
import com.physical.movement.mapper.SysuserMapper;
import com.physical.movement.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysuserMapper sysuserMapper;

    @Override
    public SysUser select(SysUser sysUser) {
        return sysuserMapper.select(sysUser);
    }

    @Override
    public Boolean insert(SysUser sysUser) {
        return sysuserMapper.insert(sysUser);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysuserMapper.updateByPrimaryKeySelective(record);
    }
}
