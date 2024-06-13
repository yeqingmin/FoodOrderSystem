// import echarts from "echarts";

const chartDom = $('#main'); // 使用jQuery选择器
const myChart = echarts.init(chartDom[0]); // 确保使用DOM元素
// 初始图表配置
const option = {
    xAxis: {
        type: 'category',
        boundaryGap: false
    },
    yAxis: {
        type: 'value',
        min: 0,
        max: 100
    },
    series: [
        {
            data: [], // 初始化为空数组
            type: 'line',
            areaStyle: {}
        }
    ]
};

function updateChart(obj) {
    $.ajax({
        url:  path + "/jsp/dish",
        type: 'GET',
        data: {method:"viewPrice",dishId: obj.attr("dishId")},
        dataType: 'json',
        success: function(data) {
            // 更新x轴和y轴的数据
            var xAxisData = data.map(function(item) {
                // 根据createTime格式化日期显示，这里需要根据实际时间格式调整
                return item.validTime;
            });
            var seriesData = data.map(function(item) {
                return item.price;
            });

            // 更新图表配置
            option.xAxis.data = xAxisData;
            option.series[0].data = seriesData;

            // 应用新的配置
            myChart.setOption(option);
        },
        error: function(xhr, status, error) {
            console.error('请求数据失败:', error);
        }
    });
}

$(function() {
    $('#update').on('click', function() {
        const obj = $(this);
        updateChart(obj);
    });


});

// var chartDom = document.getElementById('main');
// var myChart = echarts.init(chartDom);
// var option;
//
// option = {
//     xAxis: {
//         type: 'category',
//         boundaryGap: false,
//         data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
//     },
//     yAxis: {
//         type: 'value'
//     },
//     series: [
//         {
//             data: [820, 932, 901, 934, 1290, 1330, 1320],
//             type: 'line',
//             areaStyle: {}
//         }
//     ]
// };
//
// $(function(){
//     option && myChart.setOption(option);
// });

