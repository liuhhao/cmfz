package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public Map selectAll(int page, int rows) {
        Map map = new HashMap();

        RowBounds rowBounds = new RowBounds(page * rows - rows, rows);
        List<Banner> list = bannerDao.selectByRowBounds(new Banner(), rowBounds);
        long total = bannerDao.selectAll().size();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void updateStatus(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerDao.deleteByPrimaryKey(id);
    }
}
