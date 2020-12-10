package com.physical.movement.service.impl;

import com.github.pagehelper.PageHelper;
import com.physical.movement.entity.Message;
import com.physical.movement.mapper.MessageMapper;
import com.physical.movement.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageMapper messageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Message record) {
        return messageMapper.insertSelective(record);
    }

    @Override
    public Message selectByPrimaryKey(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Message record) {
        return messageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Message record) {
        return messageMapper.updateByPrimaryKey(record);
    }

    @Override
    public Message select(Message message) {
        return messageMapper.select(message);
    }

    @Override
    public Boolean insert(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public List<Message> selectAll(Message message, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return messageMapper.selectAll(message);
    }
}
