
document.getElementById('favor').addEventListener('click', function() {
    if (!isFavored) {
        // 弹出收藏成功的提示框
        alert('收藏成功！');
        // 切换按钮文本为“取消收藏”
        this.value = '取消收藏';
        // 发送请求到后端添加收藏数据
        addFavorRecord(currentUserId, merchantId);
        isFavored = true;
    } else {
        // 弹出取消收藏成功的提示框
        alert('取消收藏成功！');
        // 切换按钮文本为“收藏”
        this.value = '收藏';
        // 发送请求到后端取消收藏数据
        removeFavorRecord(currentUserId, merchantId);
        isFavored = false;
    }
});
 $(function() {
//     // 处理收藏和取消收藏的按钮点击事件
//     $("#favor").on("click", function() {
//         var isFavored = $(this).data('favored'); // 检查按钮上的数据属性，判断是否已收藏
//         var merchantId = $(this).data('merchantId'); // 获取商户ID
//         //todo 需要修改增删收藏的注册的webURL地址
//         var actionUrl = isFavored ? '取消收藏的后端URL' : '添加收藏的后端URL'; // 根据收藏状态选择URL
//         var userId = '当前用户的ID'; // 替换为实际的用户ID
//
//         // 发送AJAX请求到后端
//         $.ajax({
//             type: "POST",
//             //todo 接受收藏的url
//             url: actionUrl,
//             data: { userId: userId, merchantId: merchantId },
//             success: function(data) {
//                 if (data.success) { // 假设后端返回的数据包含success属性
//                     // 切换按钮文本
//                     var newButtonText = isFavored ? '收藏' : '取消收藏';
//                     $('input#favor').val(newButtonText);
//
//                     // 切换数据属性
//                     $('input#favor').data('favored', !isFavored);
//
//                     // 弹出提示框
//                     alert(isFavored ? '取消收藏成功！' : '收藏成功！');
//                 } else {
//                     alert('操作失败，请重试！');
//                 }
//             },
//             error: function() {
//                 alert('请求失败，请稍后重试！');
//             }
//         });
//     });

    // 处理预订点餐按钮点击事件
    $("#book").on("click", function() {
        // 重定向到预订点餐页面
        // var obj=$(this);
        window.location.href = path+ '/jsp/user/orderPage.jsp'; // 替换为实际的预订点餐页面URL
    });

    backBtn = $("#back");
    backBtn.on("click",function(){
        //alert("view : "+referer);
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