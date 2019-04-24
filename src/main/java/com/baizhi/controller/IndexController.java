package com.baizhi.controller;

import com.baizhi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("returnIndex")
    @ResponseBody
    public Object returnIndex(String uid, String type, String sub_type) {
        return indexService.returnIndex(uid, type, sub_type);
    }

    @RequestMapping("wen")
    @ResponseBody
    public Object wen(String id) {
        return indexService.wen(id);
    }

    @RequestMapping("downloadChapter")
    @ResponseBody
    public Object downloadChapter(String uid, Integer chapterId) {
        return indexService.downloadChapter(uid, chapterId);
    }
}
