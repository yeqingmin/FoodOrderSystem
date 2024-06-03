var dishName = null;
var saveBtn = null;
var backBtn = null;

$(function(){
    dishName = $("#dishName");
    saveBtn = $("#save");
    backBtn = $("#back");

    dishName.next().html("*");

    saveBtn.on("click",function(){
        dishName.blur();
        //if(dishName.attr("validateStatus") === "true"){
            if(confirm("是否确认要提交数据？")){
                $("#dishForm").submit();
            }
       // }
    });

    backBtn.on("click",function(){
        if(referer !== undefined
            && null != referer
            && "" !== referer
            && "null" !== referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});