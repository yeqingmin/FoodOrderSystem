var backBtn = null;
var dishObj=null
function openYesOrNoDLG() {
	$('.zhezhao').css('display', 'block');
	$('#favorDish').fadeIn();
}

function cancelBtn() {
	$('.zhezhao').css('display', 'none');
	$('#favorDish').fadeOut();
}

function changeDLGContent(contentStr) {
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

function favorDishFromOrder(obj){
	var dishId = obj.attr('dishId');
	console.log(dishId)

	// 发送AJAX请求到后端，请求从订单中删除菜品
	$.ajax({
		type: "GET",
		url: path + "/jsp/dish",
		data: {method: 'favorDishFromOrder',dishId: dishId},
		contentType: "application/json; charset=utf-8",
		success: function (response) {
			if (response.success) {
				changeDLGContent("收藏菜品【"+obj.attr("dishName")+"】成功");
				cancelBtn();
			} else {
				alert('收藏失败');
			}
		},
		error: function (xhr, status, error) {
			changeDLGContent("对不起，收藏失败");
		}
	});

}
$(function(){
	$(".review").on("click",function(){
		var obj = $(this);
		window.location.href = path + "/jsp/dish?method=reviewDishBegin&dishId="+obj.attr("dishId");
	});
	$(".queryReview").on("click",function(){
		var obj = $(this);
		window.location.href = path + "/jsp/dish?method=queryReview&dishId="+obj.attr("dishId");
	});


	$('#no').click(function () {
		cancelBtn();
	});

	$('#yes').click(function () {
		favorDishFromOrder(dishObj);
	});
	$(".favor").on("click", function () {
		dishObj = $(this);
		console.log(dishObj.attr("dishName"))
		// changeDLGContent("你确定要收藏菜品【" + dishObj.attr("dishName") + "】吗？");
		openYesOrNoDLG();
	});


	backBtn = $("#back");
	backBtn.on("click",function(){
		//alert("view : "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});