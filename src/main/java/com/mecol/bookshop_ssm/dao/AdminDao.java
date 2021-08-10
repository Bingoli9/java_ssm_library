package com.mecol.bookshop_ssm.dao;


import com.mecol.bookshop_ssm.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao
{

    Admin selectAdminByIdAndPasswd(@Param("id")long id, @Param("passwd") String passwd);

    Admin getAdminById(long id);

    void adminRePassword(Admin admin);
}
