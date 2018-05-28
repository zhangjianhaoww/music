package tech.bilian.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.bilian.dto.Execution;
import tech.bilian.service.AdminService;
import tech.bilian.utils.CodeUtil;
import tech.bilian.utils.HttpServletRequestUtil;
import tech.bilian.pojo.Admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/local")
public class LocalController {

    @Resource
    AdminService adminService;


//    /**
//     * 绑定帐号页路由
//     *
//     * @return
//     */
//    @RequestMapping(value = "/accountbind", method = RequestMethod.GET)
//    private String accountbind() {
//        return "local/accountbind";
//    }
    /**
     * 修改密码页路由
     *
     * @return
     */
    @RequestMapping(value = "/changepsw", method = RequestMethod.GET)
    private String changepsw() {
        return "local/changepsw";
    }
    /**
     * 登录页路由
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login() {
        return "local/login";
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    private String signup() {
        return "local/signup";
    }









    @RequestMapping(value = "logincheck", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginCheck(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        String name = HttpServletRequestUtil.getString(request, "adminName");
        String password = HttpServletRequestUtil.getString(request, "password");

        System.out.println(name);
        System.out.println(password);


        if (name == null || name.trim().equals("") || password == null || password.trim().equals("")) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码不能为空");
            return modelMap;
        }

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码不争取");
            return modelMap;
        }

        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setAdminName(name);
        Execution<Admin> adminExecution = adminService.selectAdminByNameAndPwd(name, password);
        if (adminExecution.getState() <= 0) {
            modelMap.put("success", false);
            modelMap.put("errMsg", adminExecution.getStateInfo());
            return modelMap;
        }
        request.getSession().setAttribute("user", adminExecution.getModel());
        modelMap.put("success", true);
        //modelMap.put("msg", "登录成功");
        return modelMap;
    }

}

