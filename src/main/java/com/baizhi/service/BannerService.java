package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    public Map selectAll(int page, int rows);

    public void insert(Banner banner);

    public void updateStatus(Banner banner);

    public void delete(Integer id);
}
