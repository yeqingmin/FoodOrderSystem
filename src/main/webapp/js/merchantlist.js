var merchantObj;

//订单管理页面上点击删除按钮弹出删除框(merchantlist.jsp)
function deleteMerchant(obj){
	$.ajax({
		type:"GET",
		url:path+"/jsp/merchant",
		data:{method:"delmerchant",merchantid:obj.attr("merchantid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancelBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除订单【"+obj.attr("merchantcc")+"】失败");
				changeDLGContent("对不起，删除订单【"+obj.attr("merchantcc")+"】失败");
			}else if(data.delResult == "notexist"){
				//alert("对不起，订单【"+obj.attr("merchantcc")+"】不存在");
				changeDLGContent("对不起，订单【"+obj.attr("merchantcc")+"】不存在");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancelBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	$(".viewMerchant").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/merchant?method=userView&merchantId="+ obj.attr("merchantid");
	});
	
	// $(".modifyMerchant").on("click",function(){
	// 	var obj = $(this);
	// 	window.location.href=path+"/jsp/merchant?method=modify&merchantid="+ obj.attr("merchantid");
	// });
	// $('#no').click(function () {
	// 	cancelBtn();
	// });
	//
	// $('#yes').click(function () {
	// 	deleteMerchant(merchantObj);
	// });

	// $(".deleteMerchant").on("click",function(){
	// 	merchantObj = $(this);
	// 	changeDLGContent("你确定要删除订单【"+merchantObj.attr("merchantcc")+"】吗？");
	// 	openYesOrNoDLG();
	// });
	
	/*$(".deletemerchant").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除订单【"+obj.attr("merchantcc")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/merchant.do",
				data:{method:"delmerchant",merchantid:obj.attr("merchantid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除订单【"+obj.attr("merchantcc")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，订单【"+obj.attr("merchantcc")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});