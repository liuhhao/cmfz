package com.baizhi.dao;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterDao {
    public List<Chapter> selectByAlbumId(String id);

    public void insert(Chapter chapter);

    public Chapter selectOne(Integer id);
}
