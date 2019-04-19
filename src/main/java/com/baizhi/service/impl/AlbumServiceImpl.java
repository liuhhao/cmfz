package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public List<Album> selectAll() {
        return albumDao.selectAll();
    }

    @Override
    public Album selectById(String id) {
        return albumDao.selectById(id);
    }

    @Override
    public void insert(Album album) {
        albumDao.insert(album);
    }
}
