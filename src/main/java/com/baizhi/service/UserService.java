package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> selectAll();

    public Integer selectRegisterCount(Integer count);

    void regist(User user, MultipartFile file1, HttpSession session);

    Map insert(User user);
}
