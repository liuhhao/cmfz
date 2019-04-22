package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public Integer selectRegisterCount(Integer count) {
        return userDao.selectRegisterCount(count);
    }

    @Override
    public void regist(User user, MultipartFile file1, HttpSession session) {
        String s = file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        try {
            file1.transferTo(new File(session.getServletContext().getRealPath("/images/imgUser/") + uuid + s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setId(uuid);
        user.setCreateDate(new Date());
        user.setHeadImg("/images/imgUser/" + uuid + s);
        user.setSalt("??");
        user.setStatus(1);
        //System.err.println(user);
        userDao.insert(user);

    }
}
