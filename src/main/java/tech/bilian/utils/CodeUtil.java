package tech.bilian.utils;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    /**
     * 检查验证码是否和预期相符
     *
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");

        System.out.println(verifyCodeActual);
        System.out.println(verifyCodeExpected);
        if (verifyCodeActual == null || !verifyCodeActual.toLowerCase().equals(verifyCodeExpected.toLowerCase())) {
            return false;
        }
        return true;
    }
}
