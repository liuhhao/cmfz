package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    public List<User> selectAll();

    public Integer selectRegisterCount(Integer count);
}
