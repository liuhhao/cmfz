package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    @Excel(name = "编号")
    private Integer id;

    @Excel(name = "标题")
    private String title;

    @Excel(name = "文件大小")
    private String size;

    @Excel(name = "文件时长")
    private String duration;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss", width = 35)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    @Excel(name = "下载地址", width = 25)
    private String downloadPath;

    @ExcelIgnore
    private String albumId;

}
