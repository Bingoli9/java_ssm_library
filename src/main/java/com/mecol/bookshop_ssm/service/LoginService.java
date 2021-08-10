package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.entity.Admin;
import com.mecol.bookshop_ssm.entity.ReaderCard;

public interface LoginService {
    ReaderCard hasMatchReader(long id, String passwd);

    Admin hasMatchAdmin(long id, String passwd);

    Admin getAdminById(long id);


    void adminRePassword(Admin admin);

    ReaderCard getReaderById(long id);

    void readerRePassword(ReaderCard reader);
}
