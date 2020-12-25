package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Court;
import com.physical.movement.mapper.CourtMapper;
import com.physical.movement.model.SportsType;
import com.physical.movement.service.CourtService;
import com.physical.movement.utils.GetDate;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    @Override
    public void addListCourt() throws ParseException {
        List<String> TypeList = Arrays.asList("篮球", "足球", "网球", "排球");
        for (String sport : TypeList) {
            List<Court> courts = new ArrayList<>();
            for (int a = 5; a < 0; a--) {
                for (int hour = 14; hour > 21; hour = hour + 2) {
                    Court court = new Court();
                    court.setCost(50);
                    court.setCosttime(2);
                    court.setAddress(sport + a);
                    court.setGmtCreate(GetDate.getTimeTomorrow(hour));
                    court.setType(sport);
                    courts.add(court);
                }
            }
            courtMapper.addListCourt(courts);
        }
    }


}
