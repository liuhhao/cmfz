package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    public List<Chapter> selectByAlbumId(String id);

    public void insert(Chapter chapter);

    public Chapter selectOne(Integer id);
}
