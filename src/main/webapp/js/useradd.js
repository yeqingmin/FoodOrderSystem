var userName = null;
var userPassword = null;
var addBtn = null;
var backBtn = null;


$(function(){
	userName = $("#userName");
	userPassword = $("#userPassword");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	userName.next().html("*");
	userPassword.next().html("*");

	

	userName.bind("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
	}).bind("blur",function(){
		if(userName.val() != null && userName.val().length > 1
				&& userName.val().length < 10){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
		}
		
	});
	
	userPassword.bind("focus",function(){
		validateTip(userPassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
	}).bind("blur",function(){
		if(userPassword.val() != null && userPassword.val().length > 6
				&& userPassword.val().length < 20 ){
			validateTip(userPassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userPassword.next(),{"color":"red"},imgNo + " 密码输入不符合规范，请重新输入",false);
		}
	});

	
	addBtn.bind("click",function(){
		if(userName.attr("validateStatus") !== "true"){
			userName.blur();
		}else if(userPassword.attr("validateStatus") !== "true"){
			userPassword.blur();
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