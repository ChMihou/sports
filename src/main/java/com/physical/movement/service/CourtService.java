package com.physical.movement.service;

import com.physical.movement.entity.Court;

import java.text.ParseException;
import java.util.List;

public interface CourtService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Court record);

    Court selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Court record);

    int updateByPrimaryKey(Court record);

    List<Court> selectAll(Court court, int pageNum, int pageSize);

    Court select(Court court);

    void addListCourt() throws ParseException;
}
