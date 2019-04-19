package com.baizhi.entity;

import com.baizhi.dao.BannerDao;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;

    @Test
    public void contextLoads() {

        Map map = bannerService.selectAll(0, 3);
        List<Banner> list = (List<Banner>) map.get("rows");
        for (Banner banner : list) {
            System.out.println(banner);
        }
    }

    @Test
    public void test1() {
        List<Album> list = albumService.selectAll();
        for (Album album : list) {
            System.out.println(album);
        }
    }

}
