$(function() {
    const merchantAddrSelect = $(".merchantAddr");
    // merchantAddrSelect.on("change", function() {
    //     var selectedAddr = $(this).val();
    //     // 这里可以添加额外的逻辑，比如根据选中的地址做进一步操作
    // });

    $(".merchantName").on("change", function() {
        const merchantName = $('.merchantName').val();
        console.log(merchantName);
        if (merchantName!=="") {
            $.ajax({
                type: "GET",
                url: path+ "/jsp/merchant",
                data: { method: "queryJSON", merchantName: merchantName }, // 确保后端支持此方法
                dataType: "json",
                success: function(data) {
                    if (data && data.length > 0) {
                        // 清空现有的下拉列表选项
                        merchantAddrSelect.empty();

                        // 为每个商户地址创建一个新的下拉列表选项
                        data.forEach(function(merchant) {
                            var option = new Option(merchant.merchantAddr, merchant.merchantId); // 使用merchantId作为value
                            merchantAddrSelect.append(option);
                        });
                    } else {
                        // 如果没有找到商户地址，给出提示
                        merchantAddrSelect.empty().append(new Option("未找到商户地址", ""));
                    }
                },
                error: function(xhr, status, error) {
                    // 请求失败时的处理
                    console.error("请求失败: " + error);
                }
            });
        } else {
            // 如果输入框为空，清空下拉列表
            merchantAddrSelect.empty();
        }
    });

    $(".viewDish").on("click",function() {
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        window.location.href = path + "/jsp/dish?method=userView&dishId=" + obj.attr("dishid");
    });

    $(".viewDishPrice").on("click",function() {
        console.log("into viewDishPrice")
        var obj = $(this);
        window.location.href = path + "/jsp/dish?method=dishPrice&dishId=" + obj.attr("dishid");
    });
    $(".viewDishToUserAnalysis").on("click",function() {
        var obj = $(this);
        window.location.href = path + "/jsp/dish?method=dishPrice&dishId=" + obj.attr("dishid");
    });
});