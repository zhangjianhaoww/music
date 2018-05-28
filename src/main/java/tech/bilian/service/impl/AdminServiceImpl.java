package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.AdminDao;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Admin;
import tech.bilian.service.AdminService;
import tech.bilian.utils.MD5;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;



    @Override
    public Execution<Admin> selectAdminByNameAndPwd(String userName, String password) {
        if (userName==null || userName.trim().equals("") || password==null || password.trim().equals("")){
            return new Execution<>(-1, "输入数据错误");
        }
        try{
            String mdPassword = MD5.getMd5(password);

            Admin admin = adminDao.queryAdminByNameAndPwd(userName, mdPassword);
            if (admin == null){
                return new Execution<>(-3, "用户名或密码错误");
            }
            return new Execution<>(1, "登录成功", admin);

        }catch (Exception e){
            e.printStackTrace();
            return new Execution<>(-2, "数据库操作失败");
        }
        //return null;

    }

    @Override
    public Execution<Admin> selectAdminById(Long userId) {
        return null;
    }

    @Override
    public Execution<Admin> insertAdmin(Admin admin) {
        if (admin==null || admin.getAdminName()==null || admin.getAdminName().trim().equals("") || admin.getPassword()==null || admin.getPassword().trim().equals("")){
            return new Execution<>(-1, "输入数据错误");
        }
        String password = MD5.getMd5(admin.getPassword());
        admin.setPassword(password);

        try{
            int result = adminDao.insertAdmin(admin);

                return new Execution<>(1, "创建成功");

        }catch (Exception e){
            e.printStackTrace();
            return new Execution<>(-2, "数据库操作失败");
        }
        //return null;
    }

    @Override
    public Execution<Admin> updateAdmin(Long userId, String userName, String password, String newPassword) {
        return null;
    }
}
