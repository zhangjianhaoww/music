$(function(){
    var url = '/test/admin/historys';
    var pageCount = 1;
    selectPlayCount(url);

});

function selectPlayCount(url) {
    $.ajax({
        url : url,
        async : false,
        cache : false,
        type : "get",
        dataType : 'json',
        data : {
            method : "playcount"

            // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success : function(data) {
            if (data.success){
                handleList(data.playcount);
            }
        }
    })
}

function handleList(data) {
    var html = '';
    data.map(function (item, index) {
        html += '<tr class="gradeX">'
            + '<td> <a href="/test/admin/playinformation?songName='+ item.songName +'" >' + item.songName + '</a> </td>'
            + '<td>' + item.playCount + '</td>'
    })

    $("#music-wrap").html(html);
}