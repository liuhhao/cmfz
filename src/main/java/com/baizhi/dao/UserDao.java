package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> selectAll();

    public Integer selectRegisterCount(Integer count);

    void insert(User user);
}
