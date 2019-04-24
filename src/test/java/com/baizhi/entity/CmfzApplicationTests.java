package com.baizhi.entity;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.dao.BannerDao;
import com.baizhi.service.*;
import io.goeasy.GoEasy;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;
    @Autowired
    private MapUserService mapUserService;

    @Test
    public void testUser() {
        List<User> users = userService.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
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

    @Test
    public void TestEasyPoi1() {
        List<Chapter> chapters = chapterService.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"),
                Chapter.class, chapters);
        try {
            workbook.write(new FileOutputStream(new File("D:/easypoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestEasyPoi2() {
        List<Album> albums = albumService.selectAll();
        for (Album album : albums) {
            album.setImgPath("D:/idea_project2018/cmfz/src/main/webapp" + album.getImgPath());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑详情", "专辑"),
                Album.class, albums);
        try {
            workbook.write(new FileOutputStream(new File("D:/easypoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        List<MapUser> mapUsers = mapUserService.selectToMap(1);
        String s1 = JSON.toJSONString(mapUsers);
        System.out.println(s1);
    }

    @Test
    public void testGoeasy() {
        GoEasy goEasy = new GoEasy("http(s)://rest-hangzhou.goeasy.io", "BC-aa89e6a744874dab84688d9b524dfdae");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }

    @Autowired
    private MenuService menuService;

    @Test
    public void TestMenu() {
        List<Menu> menus = menuService.selectAll();
        for (Menu menu : menus) {
            System.out.println("menu = " + menu);
        }
    }

    @Autowired
    private ArticleService articleService;

    @Test
    public void TestArticle() {
        List<Article> articles = articleService.selectAll();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Autowired
    private IndexService indexService;

    @Test
    public void TestIndex() {
/*        Object o = indexService.returnIndex("bbb", "all", "ssyj");
        String s = JSON.toJSONString(o);
        System.out.println(s);*/
        Object aa = indexService.wen(null);
        String s = JSON.toJSONString(aa);
        System.out.println(s);
    }

    @Test
    public void TestMd5() {
        String s = UUID.randomUUID().toString();
        String substring = s.substring(5, 9);
        String str = DigestUtils.md5Hex("123456" + substring);
        System.out.println(str);
    }
}
