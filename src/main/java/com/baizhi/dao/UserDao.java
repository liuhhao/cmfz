package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public List<User> selectByPage(@Param("page") int page, @Param("rows") int rows);

    public int selectCount();

    public Integer selectRegisterCount(Integer count);

    void insert(User user);

    User selectOne(User user);
}
