package com.physical.movement.service;

import com.physical.movement.entity.Message;

import java.util.List;

public interface MessageService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    Message select(Message message);

    Boolean insert(Message message);

    List<Message> selectAll(Message message, int pageNum, int pageSize);
}
