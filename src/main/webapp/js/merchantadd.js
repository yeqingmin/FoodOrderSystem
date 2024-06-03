var merchantName = null;
var merchantPassword = null;
var addBtn = null;
var backBtn = null;


$(function(){
    merchantName = $("#merchantName");
    merchantPassword = $("#merchantPassword");
    addBtn = $("#add");
    backBtn = $("#back");
    merchantName.next().html("*");
    merchantPassword.next().html("*");

    merchantName.bind("focus",function(){
        validateTip(merchantName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
    }).bind("blur",function(){
        if(merchantName.val() != null && merchantName.val().length > 1
            && merchantName.val().length < 10){
            validateTip(merchantName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(merchantName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
        }

    });

    merchantPassword.bind("focus",function(){
        validateTip(merchantPassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
    }).bind("blur",function(){
        if(merchantPassword.val() != null && merchantPassword.val().length > 6
            && merchantPassword.val().length < 20 ){
            validateTip(merchantPassword.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(merchantPassword.next(),{"color":"red"},imgNo + " 密码输入不符合规范，请重新输入",false);
        }
    });

    addBtn.bind("click",function(){
        if(merchantName.attr("validateStatus") !== "true"){
            merchantName.blur();
        }else if(merchantPassword.attr("validateStatus") !== "true"){
            merchantPassword.blur();
        }else{
            if(confirm("是否确认提交数据")){
                $("#userForm").submit();
            }
        }
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