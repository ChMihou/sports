package com.physical.movement.mapper;

import com.physical.movement.entity.Advisory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdvisoryMapper extends BaseMapper<Advisory> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Advisory record);

    Advisory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advisory record);

    int updateByPrimaryKeyWithBLOBs(Advisory record);

    int updateByPrimaryKey(Advisory record);
}