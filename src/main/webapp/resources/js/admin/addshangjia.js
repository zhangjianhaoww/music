$(function(){

    var url = '/test/admin/songs';
    var method;
    var isCan = false;
    var shangjiaId = getQueryString("shangjiaId");
    if (shangjiaId != null && shangjiaId.trim() != ''){
        method = 'update';
    } else{
        method = 'add';
    }
    alert(method);

    if (method == 'update'){
        getMessage();
    }

    if (method == 'add') {
        $("#name").blur(function () {

            var name = $("#name").val();

            if (name == null || name == '') {
                var html = '';
                $("#smallll").html(html);
                isCan = false;
                return;
            }
            else {
                $.ajax({
                    url: '/test/admin/shangjias',
                    async: false,
                    cache: false,
                    type: "get",
                    dataType: 'json',
                    data: {
                        method: "search",
                        name: name

                    },
                    success: function (data) {
                        if (data.success) {
                            if (data.data[0] != null && data.data[0] != '') {
                                var html = '<small>您填写的店铺名已存在，请先重新添加。</small>';
                                $("#smallll").html(html);
                                isCan = false;
                            } else {

                                var html = '<small>您填写的店铺名符合要求，可以添加。</small>';
                                $("#smallll").html(html);
                                isCan = true;
                            }
                        } else {

                            alert(data.errMsg);
                            isCan = false;
                        }


                    }
                });
            }
        });
    }

    $("#owner_id").blur(function () {

        var ownerId = $("#owner_id").val();

        if (ownerId == null || ownerId == ''){
            var html = '';
            $("#smalll").html(html);
            return;
        }
        else if(ownerId < 1){
            var html = '<small>非连锁店</small>';
            $("#smalll").html(html);
            isCan = true;
        }
        else{
            $.ajax({
                url :'/test/admin/shangjias',
                async : false,
                cache : false,
                type : "get",
                dataType : 'json',
                data : {
                    method : "search",
                    //page : 1,
                    //ownerName : userName,
                    //ownerAddress : userAddress,
                    //phone : userPhone
                    shangjiaId : ownerId

                },
                success : function(data) {
                    if (data.success) {
                        if (data.data[0] != null && data.data[0] !='') {
                            var html = '<small>' +
                                '您选择的连锁店是：' + data.data[0].shangjiaName +
                                '。' +
                                '</small>';
                            $("#smalll").html(html);
                            isCan = true;
                        }else {

                            var html = '<small>您选择的连锁店并不存在，请先确认连锁店id或先添加。</small>';
                            $("#smalll").html(html);
                            isCan = false;
                        }

                    } else{
                        alert(data.errMsg);
                    }


                }
            });
        }
    });

    // }

    $("#submit").click(function () {
        var name = $("#name").val();
        var ownerId = $("#owner_id").val();
        // var userPhone = $("#user-phone").val();


        if (name == null || name == '' || ownerId == null || ownerId == '') {
            alert("请输入完整信息");
            return;
        }
        else if (!isCan){
            alert("请更新信息或输入正确信息");
            return;
        }
        else{
            $.ajax({
                url :'/test/admin/shangjias',
                async : false,
                cache : false,
                type : "post",
                dataType : 'json',
                data : {
                    method : method,
                    //page : 1,
                    name : name,
                    master : ownerId,
                    shangjiaId : shangjiaId

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
        //window.location.href='/test/admin/addmusic';
    });







    function getMessage() {
        $.ajax({
            url :'/test/admin/shangjias',
            async : false,
            cache : false,
            type : "get",
            dataType : 'json',
            data : {
                method : "search",
                //page : 1,
                shangjiaId : shangjiaId
                //ownerAddress : userAddress,
                //phone : userPhone

            },
            success : function(data) {
                if (data.success) {
                    if (data.data[0] != null && data.data[0] != ''){
                        $("#name").val(data.data[0].shangjiaName);
                        $("#owner_id").val(data.data[0].master);
                    }
                    else {
                        window.location.href = '/test/admin/shangjia';
                    }


                    //
                    // var html = '<small>' +
                    //     '您选择的版权方是' + data.data.ownerName +
                    //     '。' +
                    //     '</small>';
                    // $("#smalll").html(html);
                    //$("user-phone").val(data.data.phone);
                } else {

                    alert(data.errMsg);
                    window.location.href = '/test/admin/shangjia';
                    //window.location.href = '/test/admin/owner';
                }


            }
        });

    }

});
