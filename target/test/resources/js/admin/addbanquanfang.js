$(function(){

    var url = '/test/admin/songs';
    var method;
    var ownerId = getQueryString("ownerId");
    if (ownerId != null && ownerId.trim() != ''){
        method = 'update';
    } else{
        method = 'add';
    }
   // alert(method);

    if (method == 'add'){
        $("#user-name").blur(function () {
            var userName = $("#user-name").val();
            var userAddress = $("#user-address").val();
            var userPhone = $("#user-phone").val();

            if (userName == null || userName == ''){
                return;
            }
            else{
                $.ajax({
                    url :'/test/admin/owners',
                    async : false,
                    cache : false,
                    type : "get",
                    dataType : 'json',
                    data : {
                        method : "query",
                        page : 1,
                        ownerName : userName,
                        ownerAddress : userAddress,
                        phone : userPhone

                    },
                    success : function(data) {
                        if (data.success) {

                        } else {

                            alert(data.errMsg);
                        }


                    }
                });
            }
        });

        $("#user-address").blur(function () {
            var userName = $("#user-name").val();
            var userAddress = $("#user-address").val();
            var userPhone = $("#user-phone").val();

            if (userAddress == null || userAddress == ''){
                return;
            }
            else{
                $.ajax({
                    url :'/test/admin/owners',
                    async : false,
                    cache : false,
                    type : "get",
                    dataType : 'json',
                    data : {
                        method : "query",
                        page : 1,
                        ownerName : userName,
                        ownerAddress : userAddress,
                        phone : userPhone

                    },
                    success : function(data) {
                        if (data.success) {

                        } else {

                            alert(data.errMsg);
                        }


                    }
                });
            }
        });

    }

    $("#submit2").click(function () {
        var userName = $("#user-name").val();
        var userAddress = $("#user-address").val();
        var userPhone = $("#user-phone").val();


        if (userAddress == null || userAddress == '' || userName == null || userName == ''
            || userPhone == null || userPhone == '') {
            alert("请输入完整信息dgdhdh");
            return;
        }
        else{
            $.ajax({
                url :'/test/admin/owners',
                async : false,
                cache : false,
                type : "post",
                dataType : 'json',
                data : {
                    method : method,
                    //page : 1,
                    ownerName : userName,
                    ownerAddress : userAddress,
                    phone : userPhone,
                    ownerId : ownerId

                },
                success : function(data) {
                    if (data.success) {
                        alert(data.data);
                    } else {

                        alert(data.errMsg);
                    }

                    window.location.href = '/test/admin/addbanquanfang';

                }
            });
        }
    });

    if (method == 'update'){
        getMessage();
    }

    // $.ajax({
    //     url : url,
    //     async : false,
    //     cache : false,
    //     type : "get",
    //     dataType : 'json',
    //     data : {
    //         method : "search",
    //         page : 1
    //         // password : password,
    //         //verifyCodeActual : verifyCodeActual,
    //         ////是否需要做验证码校验
    //         //needVerify : needVerify
    //     },
    //     success : function(data) {
    //         if (data.success) {
    //             // $.toast('登录成功！');
    //             //alert(data.user.adminName);
    //             handleList(data.data, data.user);
    //
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



    function ownerOperation() {
        var userName = $("#user-name").val();
        var userAddress = $("#user-address").val();
        var userPhone = $("#user-phone").val();


        if (userAddress == null || userAddress == '' || userName == null || userName == ''
            || userPhone == null || userPhone == '') {
            alert("请输入完整信息dgdhdh");
            return;
        }
        else{
            $.ajax({
                url :'/test/admin/addowner',
                async : false,
                cache : false,
                type : "post",
                dataType : 'json',
                data : {
                    method : method,
                    //page : 1,
                    ownerName : userName,
                    ownerAddress : userAddress,
                    phone : userPhone,
                    ownerId : ownerId

                },
                success : function(data) {
                    if (data.success) {
                        alert(data.data);
                    } else {

                        alert(data.errMsg);
                    }


                }
            });
        }


    }



    function getMessage() {
        $.ajax({
            url :'/test/admin/owners',
            async : false,
            cache : false,
            type : "get",
            dataType : 'json',
            data : {
                method : "searchbyid",
                //page : 1,
                ownerId : ownerId,
                //ownerAddress : userAddress,
                //phone : userPhone

            },
            success : function(data) {
                if (data.success) {
                    $("#user-name").val(data.data.ownerName);
                    $("#user-address").val(data.data.ownerAddress);
                    $("#user-phone").val(data.data.phone);
                } else {

                    alert(data.errMsg);
                    window.location.href = '/test/admin/owner';
                }


            }
        });

    }

});

