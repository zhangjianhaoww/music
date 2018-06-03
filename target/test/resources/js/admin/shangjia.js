$(function(){

var url = '/test/admin/shangjias';

    var method = getQueryString("method");
var shangjiaId = getQueryString("shangjiaId");
var name = getQueryString("name");

    $.ajax({
        url : url,
        async : false,
        cache : false,
        type : "get",
        dataType : 'json',
        data : {
            method : "search"
           // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success : function(data) {
            if (data.success) {
               // $.toast('登录成功！');
                handleList(data.data);
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




    if (method == "delect" && shangjiaId > 0 && name!= null && name.trim() != ""){
        if (confirm("确定删除" + name + "?")){
            alert("删除成功");
        }
    }




    function handleList(data) {
        var html = '';
        data.map(function (item, index) {
            html += '<tr class="gradeX">'
                + '<td>' + item.shangjiaId + '</td>'
                + '<td> <a href="" >' + item.shangjiaName + '</a> </td>'
                + '<td>无</td>'
                + '<td>'
                + '<div class="tpl-table-black-operation">'
                + '<a href="/test/admin/addshangjia?shangjiaId=' + item.shangjiaId +'">'
                + '<i class="am-icon-pencil"></i> 编辑'
                + '</a>'
                + '<a href="/test/admin/shangjia?method=delect&shangjiaId='+ item.shangjiaId +'&name='+ item.shangjiaName +'" class="tpl-table-black-operation-del" >'
                + '<i class="am-icon-trash"></i> 删除'
                + '</a>'
                + '</div>'
                + '</td>'
                + '</tr>'
        });
        $("#shangjia-wrap").html(html);
    }

});