package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
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

    @Override
    public Boolean insert(SysUser sysUser) {
        return sysuserMapper.insert(sysUser);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysuserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<SysUser> selectAll(SysUser sysUser,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return sysuserMapper.selectAll(sysUser);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysuserMapper.deleteByPrimaryKey(id);
    }

}
