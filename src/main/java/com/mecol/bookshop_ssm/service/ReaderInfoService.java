package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.entity.ReaderInfo;

import java.util.ArrayList;

public interface ReaderInfoService
{

    ArrayList<ReaderInfo> readerInfos();

    ReaderInfo getReaderInfoById(long readerId);

    void editReaderInfo(ReaderInfo readerInfo);

    void addReaderInfo(ReaderInfo readerInfo);

    ReaderInfo getReaderInfoByName(String name);

    void deleteReaderInfoById(long readerId);
}
