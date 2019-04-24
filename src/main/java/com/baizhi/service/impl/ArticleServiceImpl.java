package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> selectAll() {
        return articleDao.selectAll();
    }

    @Override
    public List<Article> selectListByUid(String uid) {
        return articleDao.selectListByUid(uid);
    }
}
