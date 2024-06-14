$(function() {
    $(".dishReview").on("click", function () {
        var obj = $(this);
        window.location.href = path + "/jsp/dish?method=reviewDishBegin&dishId=" + obj.attr("dishId");
    });
});