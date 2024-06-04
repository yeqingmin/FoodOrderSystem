var backBtn = null;
var merchantObj=null
function openYesOrNoDLG() {
    $('.zhezhao').css('display', 'block');
    $('#favorMerchant').fadeIn();
}

function cancelBtn() {
    $('.zhezhao').css('display', 'none');
    $('#favorMerchant').fadeOut();
}

function changeDLGContent(contentStr) {
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}

function favorDishFromOrder(obj){
    var merchantId = obj.attr('merchantId');
    // console.log(dishId)

    // 发送AJAX请求到后端，请求从订单中删除菜品
    $.ajax({
        type: "GET",
        url: path + "/jsp/merchant",
        data: {method: 'favorMerchant',merchantId: merchantId},
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.success) {
                changeDLGContent("收藏商户【"+obj.attr("merchantName")+"】成功");
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
 $(function() {

     $(".review").on("click",function(){
         const obj = $(this);
         window.location.href = path + "/jsp/merchant?method=reviewMerchantBegin&merchantId="+obj.attr("merchantId");
     });
     $(".queryReview").on("click",function(){
         const obj = $(this);
         window.location.href = path + "/jsp/merchant?method=queryReview&merchantId="+obj.attr("merchantId");
     });

     $('#no').click(function () {
         cancelBtn();
     });

     $('#yes').click(function () {
         favorDishFromOrder(merchantObj);
     });
     $(".favor").on("click", function () {
         merchantObj = $(this);
         // console.log(dishObj.attr("dishName"))
         // changeDLGContent("你确定要收藏菜品【" + dishObj.attr("dishName") + "】吗？");
         openYesOrNoDLG();
     });



     $("#back").on("click",function(){
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
    // 处理预订点餐按钮点击事件
    $("#orderOnline").on("click", function() {
        //先提交隐藏表单交给后端merchantServlet进行一个方法：createOrder先插入一条Order数据（获取merchantId通过表单提交,userId通过获取session），然后重定向到orderPage.jsp页面
        // window.location.href = path+ '/jsp/user/orderPage.jsp'; // 替换为实际的预订点餐页面URL
        $('#orderForm').submit();
    });

    //book按钮添加ajax响应，提交隐藏表单之后交给后端merchantServlet进行一个方法：creatBook插入一条预订信息，并且当前的预订按钮变成取消预订的按钮
     // $("#bookOffline").on("click", function() {
     //     //先提交隐藏表单交给后端merchantServlet进行一个方法：createOrder先插入一条Order数据，然后重定向到
     //     window.location.href = path+ '/jsp/user/orderPage.jsp'; // 替换为实际的预订点餐页面URL
     // });




     $("#menu").on("click", function() {
         // 提交隐藏表单
         $('#menuForm').submit();
     });


});
