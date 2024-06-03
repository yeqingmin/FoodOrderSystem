var merchantName = null;
var saveBtn = null;
var backBtn = null;

$(function(){
    merchantName = $("#merchantName");
    saveBtn = $("#save");
    backBtn = $("#back");

    merchantName.next().html("*");


    merchantName.on("focus",function(){
        validateTip(merchantName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
    }).on("blur",function(){
        if(merchantName.val() != null && merchantName.val().length > 1
            && merchantName.val().length < 10){
            validateTip(merchantName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(merchantName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
        }

    });

    saveBtn.on("click",function(){
        merchantName.blur();
        if(merchantName.attr("validateStatus") === "true"){
            if(confirm("是否确认要提交数据？")){
                $("#merchantForm").attr("accept-charset", "utf-8");
                $("#merchantForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
        //alert("modify: "+referer);
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