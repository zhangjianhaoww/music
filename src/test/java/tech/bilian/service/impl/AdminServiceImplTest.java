package tech.bilian.service.impl;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Admin;
import tech.bilian.service.AdminService;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class AdminServiceImplTest extends BaseTest {
    @Resource
    AdminService adminService;

    @Test
    public void selectAdminByNameAndPwd() {
        Execution<Admin> adminExecution = adminService.selectAdminByNameAndPwd("后台测试用户1", "1234567");

        if (adminExecution.getModel() == null)

            System.out.println("null");
    }

    @Test
    public void selectAdminById() {
    }

    @Test
    public void insertAdmin() {
        Admin admin = new Admin();
        admin.setPassword("123456");
        admin.setAdminName("admin");

        adminService.insertAdmin(admin);

    }

    @Test
    public void updateAdmin() {
    }
}