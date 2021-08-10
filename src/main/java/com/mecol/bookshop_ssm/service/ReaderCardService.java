package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.entity.ReaderCard;

public interface ReaderCardService {
    ReaderCard getReadCardByName(String name);

    ReaderCard getReadCardById(long readerId);

    void editReaderCard(ReaderCard readerCard);

    void addReaderCard(ReaderCard readerCard);

    void deleteReaderCardById(long readerId);
}
