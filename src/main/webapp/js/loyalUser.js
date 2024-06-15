$(function(){

    $(".view").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/jsp/user?method=buyDistribution&userId="+ obj.attr("userId");
    });
});