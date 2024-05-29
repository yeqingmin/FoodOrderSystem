//实现对商户的增删改三个操作
var userObj;

//订单管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
    $.ajax({
        type:"GET",
        url:path+"/jsp/user",
        data:{method:"deleteUser",userId:obj.attr("userId")},
        dataType:"json",
        success:function(data){
            if(data.deleteResult === "true"){//删除成功：移除删除行
                cancelBtn();
                obj.parents("tr").remove();
            }else if(data.deleteResult === "false"){//删除失败
                changeDLGContent("对不起，删除用户【"+obj.attr("userName")+"】失败");
            }
        },
        error:function(data){
            alert("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removeUser').fadeIn();
}

function cancelBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeUser').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}

$(function(){

    $(".modifyUser").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/jsp/user?method=modifyUser&userId="+ obj.attr("userId");
    });
    $('#no').click(function () {
        cancelBtn();
    });

    $('#yes').click(function () {
        deleteUser(userObj);
    });

    $(".deleteUser").on("click",function(){
        userObj = $(this);
        changeDLGContent("你确定要删除用户【"+userObj.attr("userName")+"】吗？");
        openYesOrNoDLG();
    });
});