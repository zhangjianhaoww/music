$(function(){
    var url1 = '/test/admin/historys';
    var url2 = '/test/admin/powers';
    var pageCount = 1;
    var userId = getQueryString('userId');
    if(userId==null || userId.trim()==''){
        window.location.href = '/test/admin/index';
        return;
    }

    //alert(songName);
    getHistory(url1, userId);
    getPower(url2, userId);

});

function getHistory(url, userId) {
    $.ajax({
        url : url,
        async : false,
        cache : false,
        type : "get",
        dataType : 'json',
        data : {
            method : "search",
            userId : userId

            // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success : function(data) {
            if (data.success){
                // alert(data.histories)
                // if (data.histories == null || data.histories==''){
                //     window.location.href = '/test/admin/index';
                //     return;
                // }

                handleHistoryList(data.histories);
            }
        }
    })

}


function handleHistoryList(data) {
    var html = '';
    data.map(function (item, index) {
        html += '<tr class="gradeX">'
            + '<td> <a href="" >' + item.songName + '</a> </td>'
            + '<td>' + item.state + '</td>'
            + '<td>' + new Date(item.time) + '</td>'
    })

    $("#shangjia-wrap1").html(html);
}

function getPower(url, userId) {
    $.ajax({
        url : url,
        async : false,
        cache : false,
        type : "get",
        dataType : 'json',
        data : {
            method : "search",
            userId : userId

            // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success : function(data) {
            if (data.success){


                handlePowerList(data.data);
            }
        }
    })
}

function handlePowerList(data) {
    var html = '';
    data.map(function (item, index) {
        html += '<tr class="gradeX">'
            + '<td> <a href="" >' + item.songName + '</a> </td>'

            + '<td>' + item.song.ownerName + '</td>'
    })

    $("#shangjia-wrap2").html(html);
}