package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.dao.AdminDao;
import com.mecol.bookshop_ssm.dao.ReaderCardDao;
import com.mecol.bookshop_ssm.entity.Admin;
import com.mecol.bookshop_ssm.entity.ReaderCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService
{

    @Autowired
    private ReaderCardDao readerCardDao;
    @Autowired
    private AdminDao adminDao;


    @Override
    public ReaderCard hasMatchReader(long id, String passwd) {
        return readerCardDao.selectReaderByIdAndPasswd(id,passwd);
    }

    @Override
    public Admin hasMatchAdmin(long id, String passwd) {
        return adminDao.selectAdminByIdAndPasswd(id,passwd);
    }

    @Override
    public Admin getAdminById(long id) {
        return adminDao.getAdminById(id);
    }

    @Override
    public void adminRePassword(Admin admin) {
        adminDao.adminRePassword(admin);
    }

    @Override
    public ReaderCard getReaderById(long id) {
        return readerCardDao.getReadCardById(id);
    }

    @Override
    public void readerRePassword(ReaderCard reader) {
        readerCardDao.editReaderCard(reader);
    }

}





