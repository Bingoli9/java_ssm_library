package com.mecol.bookshop_ssm.dao;

import com.mecol.bookshop_ssm.entity.Lend;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface LendDao {
    List<Lend> getAllList();

    void deleteLendBySerNum(long serNum);

    ArrayList<Lend> getLendListById(long readerId);

    void addLend(Lend lend);

    void updateLend(Lend lend);

    long addLendAndGetId(Lend lend);

    Lend getLendByReadIdAndBookId(@Param("readerId") long readerId, @Param("bookId") long bookId);
}
