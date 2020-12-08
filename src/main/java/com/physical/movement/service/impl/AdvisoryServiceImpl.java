package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Advisory;
import com.physical.movement.mapper.AdvisoryMapper;
import com.physical.movement.service.AdvisoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvisoryServiceImpl implements AdvisoryService {

    @Resource
    AdvisoryMapper advisoryMapper;

    @Override
    public List<Advisory> selectAll(Advisory advisory, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return advisoryMapper.selectAll(advisory);
    }

    @Override
    public Advisory select(Advisory advisory) {
        return advisoryMapper.select(advisory);
    }

    @Override
    public Boolean insert(Advisory advisory) {
        return advisoryMapper.insert(advisory);
    }

    @Override
    public int updateByPrimaryKeySelective(Advisory record) {
        return advisoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return advisoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteListId(int[] ids) {
        return advisoryMapper.deleteListId(ids);
    }
}
