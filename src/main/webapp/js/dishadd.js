var dishName = null;
var addBtn = null;
var backBtn = null;


$(function(){
    dishName = $("#dishName");
    addBtn = $("#add");
    backBtn = $("#back");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    dishName.next().html("*");



    dishName.bind("focus",function(){
        validateTip(dishName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
    }).bind("blur",function(){
        if(dishName.val() != null && dishName.val().length > 1
            && dishName.val().length < 10){
            validateTip(dishName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(dishName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
        }

    });


    addBtn.bind("click",function(){
        if(dishName.attr("validateStatus") !== "true"){
            dishName.blur();
        }else if(dishPassword.attr("validateStatus") !== "true"){
            dishPassword.blur();
        }else{
            if(confirm("是否确认提交数据")){
                $("#dishForm").submit();
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