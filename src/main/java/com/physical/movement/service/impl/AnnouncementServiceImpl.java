package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Announcement;
import com.physical.movement.mapper.AnnouncementMapper;
import com.physical.movement.service.AnnouncementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    AnnouncementMapper announcementMapper;

    @Override
    public Announcement select(Announcement announcement) {
        return announcementMapper.select(announcement);
    }

    @Override
    public List<Announcement> selectAll(Announcement announcement, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return announcementMapper.selectAll(announcement);
    }

    public int deleteByPrimaryKey(Integer id) {
        return announcementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Announcement record) {
        return announcementMapper.insertSelective(record);
    }

    @Override
    public Announcement selectByPrimaryKey(Integer id) {
        return announcementMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Announcement record) {
        return announcementMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Announcement record) {
        return announcementMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteListId(int[] ids) {
        return announcementMapper.deleteListId(ids);
    }

    @Override
    public Boolean insert(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }
}
