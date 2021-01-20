package com.physical.movement.service;

import com.physical.movement.entity.Advisory;

import java.util.List;

public interface AdvisoryService {
    List<Advisory> selectAll(Advisory advisory, int pageNum, int pageSize);

    Advisory select(Advisory advisory);

    Boolean insert(Advisory advisory);

    int updateByPrimaryKeySelective(Advisory record);

    int deleteByPrimaryKey(Integer id);

    int deleteListId(int[] ids);

    int selectCount();

}
