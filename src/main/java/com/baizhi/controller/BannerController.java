package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(int page, int rows) {
        return bannerService.selectAll(page, rows);
    }

    @RequestMapping("insert")
    @ResponseBody
    public Map selectAll(Banner banner, MultipartFile file1) throws IOException {
        Map map = new HashMap();
        //1.获取源文件夹
        String oldName = file1.getOriginalFilename();
        //2.将接收的文件复制到服务器上
        String uuid = UUID.randomUUID().toString();
//        System.out.println("action 文件名"+oldName);
        String newname = uuid + oldName.substring(oldName.lastIndexOf("."));
        file1.transferTo(new File("D:\\idea_project2018\\cmfz\\src\\main\\webapp\\images\\" + newname));
        banner.setImgPath("/images/" + newname);
        Date date = new Date();
        banner.setCreateDate(date);
        banner.setStatus(1);
        try {
            bannerService.insert(banner);
            System.out.println();
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("updateStatus")
    public void updateStatus(Banner banner) {
        System.out.println(banner);
        bannerService.updateStatus(banner);
    }

    @RequestMapping("delete")
    public void delete(Integer id) {
        System.out.println(id + "======");
        bannerService.delete(id);
    }

    //导出excel表
    @RequestMapping("export")
    public void export(HttpServletResponse response) {
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

        String encode = null;
        try {
            encode = URLEncoder.encode("章节详情.xls", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + encode);// 设置文件名


        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            workbook.write(os);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
