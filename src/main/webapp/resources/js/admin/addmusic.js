$(function(){

    var url = '/test/admin/songs';
    var method;
    var isCan = false;
    var songId = getQueryString("songId");
    if (songId != null && songId.trim() != ''){
        method = 'update';
    } else{
        method = 'add';
    }
     //alert(method);

    if (method == 'update'){
        getMessage();
    }

    //if (method == 'add'){
        $("#owner").blur(function () {

            var ownerId = $("#owner").val();

            if (ownerId == null || ownerId == ''){
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
                        method : "searchbyid",
                        //page : 1,
                        //ownerName : userName,
                        //ownerAddress : userAddress,
                        //phone : userPhone
                        ownerId : ownerId

                    },
                    success : function(data) {
                        if (data.success) {
                            var html = '<small>' +
                                '您选择的版权方是' + data.data.ownerName +
                                '。' +
                                '</small>';
                            $("#smalll").html(html);
                            isCan = true;
                        } else {

                            var html = '<small>您选择的版权方并不存在，请先确认版权方id或先添加版权方</small>';
                            $("#smalll").html(html);
                            isCan = false;
                        }


                    }
                });
            }
        });



   // }

    $("#submit").click(function () {
        var songName = $("#song-name").val();
        var ownerId1 = $("#owner").val();
       // var userPhone = $("#user-phone").val();


        if (songName == null || songName == '' || ownerId1 == null || ownerId1 == '') {
            alert("请输入完整信息dgdhdh");
            return;
        }
        else if (!isCan){
            alert("请更新信息或输入正确信息");
            return;
        }
        else{
            $.ajax({
                url :'/test/admin/songs',
                async : false,
                cache : false,
                type : "post",
                dataType : 'json',
                data : {
                    method : method,
                    //page : 1,
                    songName : songName,
                    ownerId : ownerId1,
                    songId : songId

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
            url :'/test/admin/songs',
            async : false,
            cache : false,
            type : "get",
            dataType : 'json',
            data : {
                method : "searchbyid",
                //page : 1,
                songId : songId,
                //ownerAddress : userAddress,
                //phone : userPhone

            },
            success : function(data) {
                if (data.success) {
                    $("#song-name").val(data.data.songName);
                    $("#owner").val(data.data.ownerId);


                    var html = '<small>' +
                        '您选择的版权方是' + data.data.ownerName +
                        '。' +
                        '</small>';
                    $("#smalll").html(html);
                    //$("user-phone").val(data.data.phone);
                } else {

                    alert(data.errMsg);
                    //window.location.href = '/test/admin/owner';
                }


            }
        });

    }

});
