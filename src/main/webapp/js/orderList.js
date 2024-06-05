$(function() {
    $(".orderDetailView").on("click", function () {
        var obj = $(this);
        window.location.href = path + "/jsp/order?method=orderDetail&orderId=" + obj.attr("orderId");
    });
});