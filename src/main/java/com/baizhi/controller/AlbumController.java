package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Album> selectAll() {
        List<Album> albums = albumService.selectAll();
        return albums;
    }

    @RequestMapping("addAlbum")
    @ResponseBody
    public Map addAlbum(MultipartFile file1, Album album) {
        Map map = new HashMap();
        try {
            String oldName = file1.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String newname = uuid + oldName.substring(oldName.lastIndexOf("."));
            file1.transferTo(new File("D:\\idea_project2018\\cmfz\\src\\main\\webapp\\images\\" + newname));
            album.setImgPath("/images/" + newname);
            album.setId(uuid);
            album.setPublishDate(new Date());
            albumService.insert(album);
            map.put("isInsert", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isInsert", false);
        }
        return map;
    }

    @RequestMapping("addChapter")
    @ResponseBody
    public Map addChapter(MultipartFile file1, Chapter chapter, String cid, HttpSession session) {
        Map map = new HashMap();
        try {
            String oldName = file1.getOriginalFilename();
            chapter.setTitle(oldName);
            String uuid = UUID.randomUUID().toString();
            String newName = uuid + oldName.substring(oldName.lastIndexOf("."));
            String downLoadPath = newName;
            chapter.setDownloadPath(downLoadPath);
            file1.transferTo(new File("D:\\idea_project2018\\cmfz\\src\\main\\webapp\\images\\audio\\" + downLoadPath));
            chapter.setAlbumId(cid);
            chapter.setPublishDate(new Date());
            chapter.setId(null);
            long size = file1.getSize();
            chapter.setSize(getPrintSize(size));
            chapterService.insert(chapter);
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

    //算文件大小的方法
    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

    @RequestMapping("download")
    public void download(String title, HttpServletResponse response, String downloadPath) throws IOException {
        InputStream is = new FileInputStream("D:\\idea_project2018\\cmfz\\src\\main\\webapp\\images\\audio\\" + downloadPath);
        title = URLEncoder.encode(title, "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + title);
        OutputStream os = response.getOutputStream();
        while (true) {
            int i = is.read();
            if (i == -1) break;
            os.write(i);
        }
        os.close();
        is.close();
    }
    /*mvn install:install-file -Dfile=D:\feiq\Recv Files\后期项目\项目相关\jave-1.0.2.jar -DgroupId=com.baizhi -DartifactId=jave -Dversion=1.0.0 -Dpackaging=jar*/
}
