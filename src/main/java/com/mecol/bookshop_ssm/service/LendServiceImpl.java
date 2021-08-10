package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.dao.LendDao;
import com.mecol.bookshop_ssm.entity.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LendServiceImpl implements LendService
{

    @Autowired
    private LendDao lendDao;
    @Override
    public List<Lend> lendList() {
        return lendDao.getAllList();
    }

    @Override
    public void deleteLendBySerNum(long serNum) {
        lendDao.deleteLendBySerNum(serNum);
    }

    @Override
    public ArrayList<Lend> myLendList(long readerId) {
        return lendDao.getLendListById(readerId);
    }

    @Override
    public void addLend(Lend lend) {
        lendDao.addLend(lend);
    }

    @Override
    public void updateLend(Lend lend) {
        lendDao.updateLend(lend);
    }

    @Override
    public long addLendAndGetId(Lend lend) {
        return lendDao.addLendAndGetId(lend);
    }

    @Override
    public Lend getLendByReadIdAndBookId(long readerId, long bookId) {
        return lendDao.getLendByReadIdAndBookId(readerId,bookId);
    }
}
