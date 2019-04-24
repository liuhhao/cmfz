package com.baizhi.controller;

import com.baizhi.entity.MapUser;
import com.baizhi.entity.User;
import com.baizhi.service.MapUserService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MapUserService mapUserService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll() {
        System.out.println(11111);
        Map map = new HashMap();
        List<User> list = userService.selectAll();
        map.put("rows", list);
        return map;
    }

    @RequestMapping("selectRegisterCount")
    @ResponseBody
    public Map selectRegisterCount() {
        //根据传递的参数（近一周，近两周，近三周....）查询出注册的人数，然后添加进一个数组中
        Map map = new HashMap();
        Integer integer1 = userService.selectRegisterCount(7);
        Integer integer2 = userService.selectRegisterCount(14);
        Integer integer3 = userService.selectRegisterCount(21);
        int[] ints = {integer1, integer2, integer3};
        String[] str = {"近一周", "近两周", "近三周"};
        map.put("xAxisData", str);
        map.put("seriesData", ints);
        return map;
    }

    @RequestMapping("selectToMap")
    @ResponseBody
    public Map selectToMap() {
        Map map = new HashMap();
        List<MapUser> maleList = mapUserService.selectToMap(1);
        List<MapUser> femaleList = mapUserService.selectToMap(0);
        map.put("male", maleList);
        map.put("female", femaleList);
        return map;
    }

    @RequestMapping("regist")
    @ResponseBody
    public Map regist(User user, MultipartFile file1, HttpSession session) {
        Map map = new HashMap();
        try {
            userService.regist(user, file1, session);
            map.put("flag", true);
        } catch (Exception e) {
            map.put("flag", false);
        }
        return map;
    }

    @RequestMapping("login")
    @ResponseBody
    public Map login(User user) {
        System.out.println(user);
        return userService.insert(user);
    }
}
