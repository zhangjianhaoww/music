package tech.bilian.service;

import tech.bilian.dto.Execution;
import tech.bilian.pojo.Admin;

public interface AdminService {

    Execution<Admin> selectAdminByNameAndPwd(String userName, String password);


    Execution<Admin> selectAdminById(Long userId);


    Execution<Admin> insertAdmin(Admin admin);


    Execution<Admin> updateAdmin(Long userId, String userName, String password, String newPassword);

    Execution<Admin> queryAdminByUserName(String userName);
}
