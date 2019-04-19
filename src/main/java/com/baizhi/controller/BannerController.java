package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(int page, int rows) {
        return bannerService.selectAll(page, rows);
    }

    @RequestMapping("insert")
    @ResponseBody
    public Map selectAll(Banner banner, MultipartFile file1) throws IOException {
        Map map = new HashMap();
        //1.获取源文件夹
        String oldName = file1.getOriginalFilename();
        //2.将接收的文件复制到服务器上
        String uuid = UUID.randomUUID().toString();
//        System.out.println("action 文件名"+oldName);
        String newname = uuid + oldName.substring(oldName.lastIndexOf("."));
        file1.transferTo(new File("D:\\idea_project2018\\cmfz\\src\\main\\webapp\\images\\" + newname));
        banner.setImgPath("/images/" + newname);
        Date date = new Date();
        banner.setCreateDate(date);
        banner.setStatus(1);
        try {
            bannerService.insert(banner);
            System.out.println();
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("updateStatus")
    public void updateStatus(Banner banner) {
        System.out.println(banner);
        bannerService.updateStatus(banner);
    }

    @RequestMapping("delete")
    public void delete(Integer id) {
        System.out.println(id + "======");
        bannerService.delete(id);
    }
}
