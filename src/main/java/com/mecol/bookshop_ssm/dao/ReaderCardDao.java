package com.mecol.bookshop_ssm.dao;

import com.mecol.bookshop_ssm.entity.ReaderCard;
import org.apache.ibatis.annotations.Param;

public interface ReaderCardDao {
    ReaderCard selectReaderByIdAndPasswd(@Param("id")long id, @Param("passwd") String passwd);

    ReaderCard getReadCardByName(String name);

    ReaderCard getReadCardById(long readerId);

    void editReaderCard(ReaderCard readerCard);

    void addReaderCard(ReaderCard readerCard);

    void deleteReaderCardById(long readerId);

    void readerRePassword(ReaderCard reader);
}
