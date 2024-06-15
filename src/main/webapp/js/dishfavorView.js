$(function() {
    // 绑定选择栏的change事件
    // $('#timeRange').change(function() {
    //     var selectedTimeRange = $(this).val();
    //     // 发送AJAX请求到后端
    //     $.ajax({
    //         url: path+ "/jsp/dish",
    //         type: "GET",
    //         dataType: "json",
    //         data: {
    //             method: "queryByTimeRange",
    //             timeRange: selectedTimeRange
    //         },
    //         success: function(data) {
    //             // 假设data.dishes是返回的菜品列表数组
    //             const dishList = data.dishList;
    //             const table = $('.providerTable');
    //             table.empty(); // 清空原有表格内容
    //
    //             // 遍历dishList并构建新的表格行
    //             dishList.forEach(function(dish) {
    //                 table.append(
    //                     '<tr>' +
    //                     '<td><span>' + dish.dishName + '</span></td>' +
    //                     '<td><span>' + dish.onlineSales + '</span></td>' +
    //                     '<td><span>' + dish.offlineSales + '</span></td>' +
    //                     '</tr>'
    //                 );
    //             });
    //         },
    //         error: function() {
    //             alert('请求失败，请稍后重试！');
    //         }
    //     });
    // });
});