$(function() {
    $(".viewDish").on("click", function () {
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        window.location.href = path + "/jsp/dish?method=userView&dishId=" + obj.attr("dishid");
    });
});