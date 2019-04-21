package com.baizhi.service.impl;

import com.baizhi.dao.MapUserDao;
import com.baizhi.entity.MapUser;
import com.baizhi.service.MapUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapUserServiceImpl implements MapUserService {
    @Autowired
    private MapUserDao mapUserDao;

    @Override
    public List<MapUser> selectToMap(Integer male) {
        return mapUserDao.selectToMap(male);
    }
}
