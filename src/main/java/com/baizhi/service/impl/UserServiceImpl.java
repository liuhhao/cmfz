package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
        user.setPassword(DigestUtils.md5Hex(user.getPassword() + user.getName()));
        user.setSalt(user.getName());
        user.setStatus(1);
        //System.err.println(user);
        userDao.insert(user);

    }

    @Override
    public Map insert(User user) {
        Map map = new HashMap();
        try {
            user.setPassword(DigestUtils.md5Hex(user.getPassword() + user.getName()));
            User user1 = userDao.selectOne(user);
            if (user1 != null) {
                map.put("isLogin", true);
            } else {
                map.put("isLogin", false);
            }
        } catch (Exception e) {
            map.put("isLogin", false);
            e.printStackTrace();
        }
        return map;
    }
}
