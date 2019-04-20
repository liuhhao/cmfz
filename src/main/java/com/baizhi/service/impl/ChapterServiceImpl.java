package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public List<Chapter> selectByAlbumId(String id) {
        return chapterDao.selectByAlbumId(id);
    }

    @Override
    public void insert(Chapter chapter) {
        chapterDao.insert(chapter);
    }

    @Override
    public Chapter selectOne(Integer id) {
        return chapterDao.selectOne(id);
    }

    @Override
    public List<Chapter> selectAll() {
        return chapterDao.selectAll();
    }
}
