package com.baizhi.entity;

import com.baizhi.dao.BannerDao;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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

    @Test
    public void testPOI() {
        //创建文件簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //处理字体样式
        HSSFFont font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);
        font.setBold(true);
        font.setFontName("楷体");
        //处理日期格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        //创建表并给创建的表起名字
        HSSFSheet sheet = workbook.createSheet("user");
        //创建行
        String[] str = {"姓名", "年龄", "生日"};
        Row row = sheet.createRow(0);
        //创建单元格并赋值
        for (int i = 0; i < str.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(str[i]);
            cell.setCellStyle(cellStyle);
        }
        try {
            workbook.write(new File("D:/liuhao.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImport() {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("D:/liuliu.xls")));
            Sheet sheet = workbook.getSheet("banner");
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i < lastRowNum; i++) {
                Row row = sheet.getRow(i + 1);
                String title = row.getCell(0).getStringCellValue();
                double status = row.getCell(1).getNumericCellValue();
                String path = row.getCell(2).getStringCellValue();
                Date date = row.getCell(3).getDateCellValue();
                Banner banner = new Banner(null, title, path, date, (int) status);
                System.out.println(banner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
