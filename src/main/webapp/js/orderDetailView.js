$(function() {
    $(".dishReview").on("click", function () {
        var obj = $(this);
        window.location.href = path + "/jsp/dish?method=reviewDishBegin&dishId=" + obj.attr("dishId");
    });
    $(".merchantReview").on("click",function(){
        const obj = $(this);
        window.location.href = path + "/jsp/merchant?method=reviewMerchantBegin&merchantId="+obj.attr("merchantId");
    });
});