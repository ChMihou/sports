package com.physical.movement.service;

import com.physical.movement.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    Announcement select(Announcement announcement);

    List<Announcement> selectAll(Announcement announcement, int pageNum, int pageSize);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    int deleteListId(int[] ids);

    Boolean insert(Announcement announcement);
}
