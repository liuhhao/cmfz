package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map selectByPage(int page, int rows) {
        Map map = new HashMap();
        try {
            List<Album> list = albumDao.selectByPage(page * rows - rows, rows);
            int total = albumDao.selectCount();
            map.put("rows", list);
            map.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
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
