$(function() {
    $(".order").on("click", function () {
        const obj = $(this);
        window.location.href = path + "/jsp/system?method=queryOrderMessage&userId=" + obj.attr("userId");
    });
    $(".book").on("click", function () {
        const obj = $(this);
        window.location.href = path + "/jsp/system?method=queryBookMessage&userId=" + obj.attr("userId");
    });
});