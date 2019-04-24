package com.baizhi.service;

public interface IndexService {
    public Object returnIndex(String uid, String type, String sub_type);

    Object wen(String id);

    Object downloadChapter(String uid, Integer chapterId);
}
