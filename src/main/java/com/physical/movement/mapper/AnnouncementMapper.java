package com.physical.movement.mapper;

import com.physical.movement.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
}