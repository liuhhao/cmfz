package com.baizhi.controller;

import com.baizhi.entity.Record;
import com.baizhi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private RecordService recordService;

    @RequestMapping("returnIndex")
    @ResponseBody
    public Object returnIndex(String uid, String type, String sub_type) {
        if (uid == null || type == null) {
            return new Error("参数不能为空");
        } else if (type.equals("all")) {
            Map<String, Object> map = new HashMap<>();
            map.put("banner", bannerService.select());
            map.put("album", albumService.selectAll());
            map.put("article", articleService.selectAll());
            return map;
        } else if (type.equals("wen")) {
            Map<String, Object> map = new HashMap<>();
            map.put("album", albumService.selectAll());
            return map;
        } else {
            if (sub_type == null) {
                return new Error("思类型参数为空");
            } else if (sub_type.equals("ssyj")) {
                Map<String, Object> map = new HashMap<>();
                map.put("article", articleService.selectListByUid(uid));
                return map;
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("article", articleService.selectAll());
                return map;
            }
        }
    }

    @RequestMapping("wen")
    @ResponseBody
    public Object wen(String id) {
        if (id == null) {
            return new Error("未选择专辑");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("introduction", albumService.selectById(id));
            map.put("list", chapterService.selectAll());
            return map;
        }
    }

    @RequestMapping("downloadChapter")
    @ResponseBody
    public Object downloadChapter(String uid, Integer chapterId) {
        if (chapterId != null) {
            if (uid == null) {
                return new Error("没有登录");
            } else {
                Map<String, Object> map = new HashMap<>();
                Record record = new Record();
                record.setUid(uid);
                record.setCid(chapterId);
                recordService.insert(record);
                map.put("download", "ok");
                return map;
            }
        } else {
            return new Error("未选择章节");
        }
    }
}
