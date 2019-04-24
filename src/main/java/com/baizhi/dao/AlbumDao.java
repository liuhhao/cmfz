package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    public List<Album> selectByPage(@Param("page") int page, @Param("rows") int rows);
    public List<Album> selectAll();

    public int selectCount();

    public Album selectById(String id);

    public void insert(Album album);
}
