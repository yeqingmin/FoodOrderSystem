$(function() {
    $(".week").on("click", function () {
        const obj = $(this);
        window.location.href = path + "/jsp/order?method=weekFrequency";
    });
    $(".month").on("click", function () {
        const obj = $(this);
        window.location.href = path + "/jsp/order?method=monthFrequency";
    });
    $(".activity").on("click", function () {
        const obj = $(this);
        window.location.href = path + "/jsp/order?method=timeZone";
    });
});