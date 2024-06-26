//实现对商户的增删改三个操作
var merchantObj;

function deleteMerchant(obj){
    $.ajax({
        type:"GET",
        url:path+"/jsp/merchant",
        data:{method:"deleteMerchant",merchantId:obj.attr("merchantId")},
        dataType:"json",
        success:function(data){
            if(data.deleteResult === "true"){//删除成功：移除删除行
                cancelBtn();
                obj.parents("tr").remove();
            }else if(data.deleteResult === "false"){//删除失败
                changeDLGContent("对不起，删除商户【"+obj.attr("merchantName")+"】失败");
            }
        },
        error:function(data){
            alert("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removeMerchant').fadeIn();
}

function cancelBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeMerchant').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}

$(function(){

    $(".modifyMerchant").on("click",function(){
    	var obj = $(this);
    	window.location.href=path+"/jsp/merchant?method=modifyMerchant&merchantId="+ obj.attr("merchantId");
    });
    $('#no').click(function () {
    	cancelBtn();
    });

    $('#yes').click(function () {
    	deleteMerchant(merchantObj);
    });

    $(".deleteMerchant").on("click",function(){
    	merchantObj = $(this);
    	changeDLGContent("你确定要删除商户【"+merchantObj.attr("merchantName")+"】吗？");
    	openYesOrNoDLG();
    });
});