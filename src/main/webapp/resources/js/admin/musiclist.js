$(function(){

    var url = '/test/admin/songs'

    $.ajax({
        url : url,
        async : false,
        cache : false,
        type : "get",
        dataType : 'json',
        data : {
            method : "search",
            page : 1
            // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success : function(data) {
            if (data.success) {
                // $.toast('登录成功！');
                //alert(data.user.adminName);
                handleList(data.data, data.user);

                //if (usertype == 1) {
                // 若用户在前端展示系统页面则自动链接到前端展示系统首页
                //window.location.href = '/test/admin/index';
                //} else {
                // 若用户是在店家管理系统页面则自动链接到店铺列表页中
                //   window.location.href = '/o2o/shopadmin/shoplist';
                //}
            } else {
                //  $.toast('登录失败！' + data.errMsg);
                // loginCount++;
                //if (loginCount >= 3) {
                // 登录失败三次，需要做验证码校验
                //   $('#verifyPart').show();
                // }
            }


        }
    });

    function handleList(data, user) {
        var html = '';
        data.map(function (item, index) {
            html += '<tr class="gradeX">'
                + '<td>' + item.songId + '</td>'
                + '<td> <a href="/test/admin/playinformation?songName='+ item.songName +'" >' + item.songName + '</a> </td>'
                + '<td>' + item.ownerName  + '</td>'
                + '<td>'
                + '<div class="tpl-table-black-operation">'
                + '<a href="/test/admin/addmusic?songId='+ item.songId +'">'
                + '<i class="am-icon-pencil"></i> 编辑'
                + '</a>'
                + '<a href="javascript:;" class="tpl-table-black-operation-del">'
                + '<i class="am-icon-trash"></i> 删除'
                + '</a>'
                + '</div>'
                + '</td>'
                + '</tr>'
        });
        $("#music-wrap").html(html);
        $("#user_name_head").html('<a href=\"javascript:;\">欢迎你, <span>' + user.adminName + '</span> </a>');
        $("#user_name").html('<i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>' + user.adminName);
    }

});