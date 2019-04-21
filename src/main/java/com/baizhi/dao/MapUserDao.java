package com.baizhi.dao;

import com.baizhi.entity.MapUser;

import java.util.List;

public interface MapUserDao {
    public List<MapUser> selectToMap(Integer male);
}
