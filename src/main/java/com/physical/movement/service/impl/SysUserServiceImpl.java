package com.physical.movement.service.impl;

import com.physical.movement.entity.SysUser;
import com.physical.movement.mapper.SysuserMapper;
import com.physical.movement.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysuserMapper sysuserMapper;

    @Override
    public SysUser select(SysUser sysUser) {
        return sysuserMapper.select(sysUser);
    }
}
