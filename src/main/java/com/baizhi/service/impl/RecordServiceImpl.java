package com.baizhi.service.impl;

import com.baizhi.dao.RecordDao;
import com.baizhi.entity.Record;
import com.baizhi.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public void insert(Record record) {
        recordDao.insert(record);
    }
}
