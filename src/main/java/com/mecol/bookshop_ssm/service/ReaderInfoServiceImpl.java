package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.dao.ReaderInfoDao;
import com.mecol.bookshop_ssm.entity.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderInfoServiceImpl implements ReaderInfoService
{
    @Autowired
    private ReaderInfoDao readerInfoDao;


    @Override
    public ArrayList<ReaderInfo> readerInfos() {
        return readerInfoDao.getAllReaderInfo();
    }

    @Override
    public ReaderInfo getReaderInfoById(long readerId) {
        return readerInfoDao.getReaderInfoById(readerId);
    }

    @Override
    public void editReaderInfo(ReaderInfo readerInfo) {
        readerInfoDao.editReaderInfo(readerInfo);
    }

    @Override
    public void addReaderInfo(ReaderInfo readerInfo) {
        readerInfoDao.addReaderInfo(readerInfo);
    }

    @Override
    public ReaderInfo getReaderInfoByName(String name) {
        return readerInfoDao.getReaderInfoByName(name);
    }

    @Override
    public void deleteReaderInfoById(long readerId) {
        readerInfoDao.deleteReaderInfoById(readerId);
    }
}
