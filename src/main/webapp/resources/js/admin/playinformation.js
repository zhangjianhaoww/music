$(function(){
    var url = '/test/admin/historys';
    var pageCount = 1;
    var songName = getQueryString('songName');
    if(songName==null || songName.trim()==''){
        window.location.href = '/test/admin/index';
        return;
    }

    //alert(songName);
    selectPlayCount(url, songName);

});

function selectPlayCount(url, songName) {
    $.ajax({
        url : url,
        async : false,
        cache : false,
        type : "get",
        dataType : 'json',
        data : {
            method : "search",
            songName : songName

            // password : password,
            //verifyCodeActual : verifyCodeActual,
            ////是否需要做验证码校验
            //needVerify : needVerify
        },
        success : function(data) {
            if (data.success){
               // alert(data.histories)
                if (data.histories == null || data.histories==''){
                    window.location.href = '/test/admin/index';
                    return;
                }

                handleList(data.histories);
            }
        }
    })
}

function handleList(data) {
    var html = '';
    data.map(function (item, index) {
        html += '<tr class="gradeX">'
            + '<td> <a href="" >' + item.shangjia.shangjiaName + '</a> </td>'
            + '<td>' + item.state + '</td>'
            + '<td>' + new Date(item.time) + '</td>'
    })

    $("#music-wrap").html(html);
}