package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.dao.ReaderCardDao;
import com.mecol.bookshop_ssm.entity.ReaderCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderCardServiceImpl implements ReaderCardService
{
    @Autowired
    private ReaderCardDao readerCardDao;


    @Override
    public ReaderCard getReadCardByName(String name) {
        return readerCardDao.getReadCardByName(name);
    }

    @Override
    public ReaderCard getReadCardById(long readerId) {
        return readerCardDao.getReadCardById(readerId);
    }

    @Override
    public void editReaderCard(ReaderCard readerCard) {
        readerCardDao.editReaderCard(readerCard);
    }

    @Override
    public void addReaderCard(ReaderCard readerCard) {
        readerCardDao.addReaderCard(readerCard);
    }

    @Override
    public void deleteReaderCardById(long readerId) {
        readerCardDao.deleteReaderCardById(readerId);
    }
}
