package com.mecol.bookshop_ssm.dao;

import com.mecol.bookshop_ssm.entity.ReaderInfo;

import java.util.ArrayList;

public interface ReaderInfoDao {
    ArrayList<ReaderInfo> getAllReaderInfo();

    ReaderInfo getReaderInfoById(long readerId);

    void editReaderInfo(ReaderInfo readerInfo);

    void addReaderInfo(ReaderInfo readerInfo);

    ReaderInfo getReaderInfoByName(String name);

    void deleteReaderInfoById(long readerId);
}
