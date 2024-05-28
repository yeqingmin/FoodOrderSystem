function openYesOrNoDLG() {
    $('.zhezhao').css('display', 'block');
    $('#removeDish').fadeIn();
}

function cancelBtn() {
    $('.zhezhao').css('display', 'none');
    $('#removeDish').fadeOut();
}

function changeDLGContent(contentStr) {
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
var dishObj;
var quantityObj;
function deleteDishFromOrder(obj,quantityobj){
    var dishId = obj.attr('dishId');
    var orderId = obj.attr('orderId');

    // 发送AJAX请求到后端，请求从订单中删除菜品
    $.ajax({
        type: "GET",
        url: path + "/jsp/dish",
        data: {method: 'deleteDishFromOrder', orderId: orderId, dishId: dishId},
        success: function (response) {
            if (response.success) {
                changeDLGContent("删除菜品【"+obj.attr("dishName")+"】成功");
                cancelBtn();
                quantityobj.text(response.quantity);
            } else {
                alert('删除失败');
            }
        },
        error: function (xhr, status, error) {
            changeDLGContent("对不起，删除失败");
        }
    });

}
$(function () {

    // 绑定点击事件到添加菜品按钮
    $('.addDishToOrder').on('click', function () {
        var dishId = $(this).attr('dishId');
        var orderId = $(this).attr('orderId');

        // 发送AJAX请求到后端，请求添加菜品到订单
        $.ajax({
            type: "GET",
            url: path + "/jsp/dish",
            data: {method: 'addDishToOrder', orderId: orderId, dishId: dishId},
            success: function (response) {
                if (response.success) {
                    // 假设响应中包含更新后的数量
                    var newQuantity = response.quantity;
                    updateQuantityDisplay(dishId, newQuantity);
                } else {
                    alert('添加失败: ' + response.message);
                }
            },
            error: function (xhr, status, error) {
                alert('添加菜品时发生错误');
            }
        });
    });


    // 函数：更新页面上的数量显示
    function updateQuantityDisplay(dishId, quantity) {
        quantityObj= $(".quantityForDish[dishId='"+ dishId + "']");
        quantityObj.text(quantity);
    }

    $('#no').click(function () {
        cancelBtn();
    });

    $('#yes').click(function () {
        deleteDishFromOrder(dishObj,quantityObj);
    });
    $(".deleteDishFromOrder").on("click", function () {
        dishObj = $(this);
        changeDLGContent("你确定要删除菜品【" + dishObj.attr("dishName") + "】吗？");
        openYesOrNoDLG();
    });

    backBtn = $("#back");
    backBtn.on("click", function () {
        //alert("view : "+referer);
        if (referer !== undefined
            && null != referer
            && "" !== referer
            && "null" !== referer
            && referer.length > 4) {
            window.location.href = referer;
        } else {
            history.back(-1);
        }
    });
});

