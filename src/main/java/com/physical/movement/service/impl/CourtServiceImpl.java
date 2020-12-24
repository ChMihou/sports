package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Court;
import com.physical.movement.mapper.CourtMapper;
import com.physical.movement.service.CourtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourtServiceImpl implements CourtService {

    @Resource
    CourtMapper courtMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return courtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Court record) {
        return courtMapper.insertSelective(record);
    }

    @Override
    public Court selectByPrimaryKey(Integer id) {
        return courtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Court record) {
        return courtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Court record) {
        return courtMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Court> selectAll(Court court, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return courtMapper.selectAll(court);
    }

    @Override
    public Court select(Court court) {
        return courtMapper.select(court);
    }
}
