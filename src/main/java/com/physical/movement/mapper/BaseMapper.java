package com.physical.movement.mapper;

import com.physical.movement.entity.common.Id;

import java.util.List;

public interface BaseMapper<T extends Id> {

    //插入
    public Boolean insert(T t);

    //更新
    public Boolean update(T t);

    //删除
    public Boolean delete(Integer id);

    //查找
    public T select(T t);

    public List<T> selectAll(T t);

    int selectCount();

}
