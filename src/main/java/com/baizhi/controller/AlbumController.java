package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import com.baizhi.service.ChapterService;
import com.baizhi.util.AudioUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private BannerService bannerService;

    //查询所有专辑
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Album> selectAll() {
        List<Album> albums = albumService.selectAll();
        return albums;
    }

    //添加专辑
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

    //添加章节
    @RequestMapping("addChapter")
    @ResponseBody
    public Map addChapter(MultipartFile file1, Chapter chapter, String cid, HttpSession session) {
        Map map = new HashMap();
        try {
            long size = file1.getSize();
            String path = session.getServletContext().getRealPath("/");
            String dir = path + "audio";
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdir();
            }
            //重命名
            String title = file1.getOriginalFilename();
            String extension = FilenameUtils.getExtension(title);
            String newName = UUID.randomUUID().toString() + "." + extension;
            File file2 = null;
            try {
                file2 = new File(dir, newName);
                file1.transferTo(file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            chapter.setDownloadPath(newName);
            String duration = AudioUtil.getDuration(file2);
            chapter.setId(null);
            chapter.setTitle(title);
            chapter.setSize(getPrintSize(size));
            chapter.setDuration(duration);
            chapter.setPublishDate(new Date());
            chapter.setAlbumId(cid);
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

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    //下载
    @RequestMapping("download")
    public void download(String title, HttpServletResponse response, String downloadPath, HttpSession session) {

        String realPath = session.getServletContext().getRealPath("/audio");
        String fileName = realPath + "/" + downloadPath;
        File file = new File(fileName);
        String extension = FilenameUtils.getExtension(downloadPath);
        String oldName = title + "." + extension;
        String encode = "";
        try {
            encode = URLEncoder.encode(oldName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        response.setContentType("audio/mpeg");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导出excel表
    @RequestMapping("export")
    @ResponseBody
    public Map export() {
        Map map = new HashMap();
        try {
            //创建文件簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //处理字体样式
            HSSFFont font = workbook.createFont();
            font.setFontName("楷体");
            font.setBold(true);
            font.setItalic(true);
            font.setColor(Font.COLOR_RED);
            HSSFFont font1 = workbook.createFont();
            font1.setFontName("楷体");
            font1.setBold(true);
            font1.setItalic(true);
            //处理标题的样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            //处理表格内容的样式以及时间的格式
            HSSFDataFormat dataFormat = workbook.createDataFormat();
            short format = dataFormat.getFormat("yyyy-MM-dd");
            HSSFCellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setDataFormat(format);
            cellStyle1.setAlignment(HorizontalAlignment.CENTER);
            //普通表格数据的处理
            HSSFCellStyle cellStyle2 = workbook.createCellStyle();
            cellStyle2.setFont(font1);
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            //创建表
            HSSFSheet sheet = workbook.createSheet("banner");
            //创建行
            String[] str = {"标题", "状态(0是不激活/1是激活)", "路径", "时间"};
            Row row = sheet.createRow(0);
            for (int i = 0; i < str.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(str[i]);
                cell.setCellStyle(cellStyle);
            }
            //填充表格数据
            List<Banner> banners = bannerService.select();
            for (int i = 0; i < banners.size(); i++) {
                HSSFRow row1 = sheet.createRow(i + 1);
                Cell cell1 = row1.createCell(0);
                cell1.setCellStyle(cellStyle2);
                cell1.setCellValue(banners.get(i).getTitle());
                Cell cell2 = row1.createCell(1);
                cell2.setCellStyle(cellStyle2);
                cell2.setCellValue(banners.get(i).getStatus());
                Cell cell3 = row1.createCell(2);
                cell3.setCellValue(banners.get(i).getImgPath());
                cell3.setCellStyle(cellStyle2);
                Cell cell = row1.createCell(3);
                cell.setCellStyle(cellStyle1);
                cell.setCellValue(banners.get(i).getCreateDate());
            }
            workbook.write(new File("D:/liuliu.xls"));
            System.out.println(1111);
            map.put("isExport", true);
        } catch (Exception e) {
            map.put("isExport", false);
            e.printStackTrace();
        }
        return map;
    }
}
