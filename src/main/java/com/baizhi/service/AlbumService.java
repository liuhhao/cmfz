package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Map selectByPage(int page, int rows);
    public List<Album> selectAll();

    public Album selectById(String id);

    public void insert(Album album);
}
