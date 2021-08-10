package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.entity.Lend;

import java.util.ArrayList;
import java.util.List;

public interface LendService {

    List<Lend> lendList();

    void deleteLendBySerNum(long serNum);

    ArrayList<Lend> myLendList(long readerId);

    void addLend(Lend lend);

    void updateLend(Lend lend);

    long addLendAndGetId(Lend lend);

    Lend getLendByReadIdAndBookId(long readerId, long bookId);
}
