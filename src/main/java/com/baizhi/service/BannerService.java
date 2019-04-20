package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    //分页查询所有
    public Map selectAll(int page, int rows);

    public void insert(Banner banner);

    public void updateStatus(Banner banner);

    public void delete(Integer id);

    //查询所有
    public List<Banner> select();
}
