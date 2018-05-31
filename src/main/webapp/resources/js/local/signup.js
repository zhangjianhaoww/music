$(function() {

    var url = '/test/local/signupcheck'

    // $.ajax({
    //     url: url,
    //     async: false,
    //     cache: false,
    //     type: "get",
    //     dataType: 'json',
    //     data: {
    //         method: "search"
    //         // password : password,
    //         //verifyCodeActual : verifyCodeActual,
    //         ////是否需要做验证码校验
    //         //needVerify : needVerify
    //     },
    //     success: function (data) {
    //         if (data.success) {
    //             // $.toast('登录成功！');
    //             handleList(data.data);
    //             //if (usertype == 1) {
    //             // 若用户在前端展示系统页面则自动链接到前端展示系统首页
    //             //window.location.href = '/test/admin/index';
    //             //} else {
    //             // 若用户是在店家管理系统页面则自动链接到店铺列表页中
    //             //   window.location.href = '/o2o/shopadmin/shoplist';
    //             //}
    //         } else {
    //             //  $.toast('登录失败！' + data.errMsg);
    //             // loginCount++;
    //             //if (loginCount >= 3) {
    //             // 登录失败三次，需要做验证码校验
    //             //   $('#verifyPart').show();
    //             // }
    //         }
    //
    //
    //     }
    // });


});


function checkUser() {
    var userName = $('#username').val();
    if (userName == null || userName == ''){
        alert("输入不能为空");
    } else{
        $.ajax({
            url: '/test/local/usercheck',
            async: false,
            cache: false,
            type: "get",
            dataType: 'json',
            data: {
                //method: "search",
                userName: userName
                // password : password,
                //verifyCodeActual : verifyCodeActual,
                ////是否需要做验证码校验
                //needVerify : needVerify
            },
            success: function (data) {
                if (data.success) {
                    $.toast('该用户名可以注册！');
                    //alert("该用户名可以注册");
                    // $.toast('登录成功！');
                    // handleList(data.data);
                    //if (usertype == 1) {
                    // 若用户在前端展示系统页面则自动链接到前端展示系统首页
                    //window.location.href = '/test/admin/index';
                    //} else {
                    // 若用户是在店家管理系统页面则自动链接到店铺列表页中
                    //   window.location.href = '/o2o/shopadmin/shoplist';
                    //}
                } else {
                    //alert("该用户已存在，请更换");
                    $.toast('该用户已存在，请更换！');
                    //  $.toast('登录失败！' + data.errMsg);
                    // loginCount++;
                    //if (loginCount >= 3) {
                    // 登录失败三次，需要做验证码校验
                    //   $('#verifyPart').show();
                    // }
                }


            }
        });
    }

}

function signup() {
    var password = $('#psw').val();
    // 获取验证码信息
    var verifyCodeActual = $('#j_captcha').val();

    var adminName = $('#username').val();

    var repassword = $('#repsw').val();

    if (password != repassword){
        alert("两次密码不相同");
        return;
    }

    if(password == null || adminName == null || repassword == null || verifyCodeActual == null){
        alert("请输入完整数据");
        return;
    }

    $.ajax({
        url: '/test/local/usersignin',
        async: false,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            //method: "search",
            adminName: adminName,
            password: password,
            verifyCodeActual : verifyCodeActual

            // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success: function (data) {
            if (data.success) {
                //alert("该用户名可以注册");
                 $.toast('注册成功！');
                // handleList(data.data);
                //if (usertype == 1) {
                // 若用户在前端展示系统页面则自动链接到前端展示系统首页
                window.location.href = '/test/local/login';
                //} else {
                // 若用户是在店家管理系统页面则自动链接到店铺列表页中
                //   window.location.href = '/o2o/shopadmin/shoplist';
                //}
            } else {
                //alert(data.errMsg);
                $.toast(data.errMsg);

                //  $.toast('登录失败！' + data.errMsg);
                // loginCount++;
                //if (loginCount >= 3) {
                // 登录失败三次，需要做验证码校验
                //   $('#verifyPart').show();
                // }
            }


        }
    });
}