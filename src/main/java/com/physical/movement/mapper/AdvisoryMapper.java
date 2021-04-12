package com.physical.movement.mapper;

import com.physical.movement.entity.Advisory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvisoryMapper extends BaseMapper<Advisory> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Advisory record);

    Advisory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advisory record);

    int updateByPrimaryKey(Advisory record);

    int deleteListId(int[] ids);

    List<String> selectAllImage();
}