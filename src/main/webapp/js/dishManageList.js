//实现对商户的增删改三个操作
var dishObj;

//订单管理页面上点击删除按钮弹出删除框(dishlist.jsp)
function deleteDish(obj){
    $.ajax({
        type:"GET",
        url:path+"/jsp/dish",
        data:{method:"deleteDish",dishId:obj.attr("dishId")},
        dataType:"json",
        success:function(data){
            if(data.deleteResult === "true"){//删除成功：移除删除行
                cancelBtn();
                obj.parents("tr").remove();
            }else if(data.deleteResult === "false"){//删除失败
                changeDLGContent("对不起，删除菜品【"+obj.attr("dishName")+"】失败");
            }
        },
        error:function(data){
            alert("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removeDish').fadeIn();
}

function cancelBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeDish').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}

$(function(){

    $(".modifyDish").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/jsp/dish?method=modifyDish&dishId="+ obj.attr("dishId");
    });
    $('#no').click(function () {
        cancelBtn();
    });

    $('#yes').click(function () {
        deleteDish(dishObj);
    });

    $(".deleteDish").on("click",function(){
        dishObj = $(this);
        changeDLGContent("你确定要删除菜品【"+dishObj.attr("dishName")+"】吗？");
        openYesOrNoDLG();
    });
});