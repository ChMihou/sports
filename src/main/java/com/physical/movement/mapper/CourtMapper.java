package com.physical.movement.mapper;

import com.physical.movement.entity.Court;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourtMapper extends BaseMapper<Court> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Court record);

    Court selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Court record);

    int updateByPrimaryKey(Court record);
}