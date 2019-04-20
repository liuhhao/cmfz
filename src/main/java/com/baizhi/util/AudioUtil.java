package com.baizhi.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * @Author: buxy
 * @Date: 2019/4/16  1:03
 */
public class AudioUtil {
    public static String getDuration(File source) {
        Encoder encoder = new Encoder();
        String time = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration() / 1000;
            long hh = ls / 3600;
            long mm = ls % 3600 / 60;
            long ss = ls % 60;
            time = hh + ":" + mm + ":" + ss;
        } catch (EncoderException e) {
            e.printStackTrace();
            System.out.println("获取文件时长失败：" + e.getMessage());
        }
        return time;
    }
}