package com.baizhi.service;

import com.baizhi.entity.MapUser;

import java.util.List;

public interface MapUserService {
    public List<MapUser> selectToMap(Integer male);
}
