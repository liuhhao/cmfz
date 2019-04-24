package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> selectAll();

    public List<Article> selectListByUid(String uid);
}
