package tech.bilian.dao;

import org.apache.ibatis.annotations.Param;
import tech.bilian.pojo.Admin;

public interface AdminDao {
    /**
     * 通过帐号和密码查询对应信息，登录用
     *
     * @param username
     * @param password
     * @return
     */
    Admin queryAdminByNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户Id查询对应localauth
     *
     * @param userId
     * @return
     */
    Admin queryAdminByUserId(@Param("userId") long userId);

    /**
     * 添加平台帐号
     *
     * @param
     * @return
     */
    int insertAdmin(@Param("admin") Admin admin);

    /**
     * 通过userId,username,password更改密码
     *
     * @param
     * @return
     */
    int updateAdmin(@Param("userId") Long userId, @Param("username") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword);



    Admin queryAdminByUserName(@Param("userName") String userName);
}
