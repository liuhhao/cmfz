package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "专辑")
public class Album {
    @ExcelIgnore
    private String id;
    @Excel(name = "标题", needMerge = true)
    private String title;
    @Excel(name = "专辑章节数", needMerge = true)
    private Integer amount;
    @Excel(name = "图片地址", needMerge = true, type = 2, width = 40, height = 20)
    private String imgPath;
    @Excel(name = "专辑的评分", needMerge = true)
    private Integer score;
    @Excel(name = "作者", needMerge = true)
    private String author;
    @Excel(name = "播音员", needMerge = true)
    private String boardcast;
    @Excel(name = "推送时间", needMerge = true, format = "yyyy-MM-dd HH:mm:ss", width = 35)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    @Excel(name = "专辑的简介", needMerge = true)
    private String brief;
    @ExcelCollection(name = "具体章节")
    private List<Chapter> children;
}
