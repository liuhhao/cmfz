package com.baizhi.dao;

import com.baizhi.entity.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleDao extends Mapper<Article> {
    public List<Article> selectListByUid(String uid);
}
