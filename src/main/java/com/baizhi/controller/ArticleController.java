package com.baizhi.controller;

import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

}
