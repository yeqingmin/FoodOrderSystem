var userName = null;
var saveBtn = null;
var backBtn = null;

$(function(){
	userName = $("#userName");
	saveBtn = $("#save");
	backBtn = $("#back");
	
	userName.next().html("*");


	userName.on("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
	}).on("blur",function(){
		if(userName.val() != null && userName.val().length > 1 
				&& userName.val().length < 10){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
		}
		
	});

	saveBtn.on("click",function(){
		userName.blur();
		if(userName.attr("validateStatus") === "true"){
			if(confirm("是否确认要提交数据？")){
				$("#userForm").attr("accept-charset", "utf-8");
				$("#userForm").submit();
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