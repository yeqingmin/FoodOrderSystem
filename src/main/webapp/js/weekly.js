const chartDom = $('#main');
const myChart = echarts.init(chartDom[0]);

// 定义option的初始状态
const option = {
    xAxis: {
        type: 'category',
        data:  ['the last third week', 'the last second week', 'the last week', 'current week'] // 这里假设你已经知道这四个月的名称
    },
    yAxis: {
        type: 'value',
        min: 0,
        max: 30
    },
    series: [
        {
            data: [], // 初始化为空数组，稍后用Ajax获取的数据填充
            type: 'line',
            areaStyle: {}
        }
    ]
};

// 确保option设置完毕后才调用setOption
option && myChart.setOption(option);

// 定义updateChart函数，用于更新图表数据
function updateChart(obj) {
    // 使用jQuery的Ajax方法调用后端接口
    $.ajax({
        url:  path + "/jsp/order",
        type: 'GET',
        data: {method:"weekReact"},
        dataType: 'json',
        success: function(response) {
            // 假设后端返回的数据格式是一个包含四个整数的数组
            if (Array.isArray(response) && response.length === 4) {
                // 更新x轴和y轴的数据
                option.xAxis.data =   ['the last third week', 'the last second week', 'the last week', 'current week'];
                option.series[0].data = response;

                // 使用新的option更新图表
                myChart.setOption(option);
            } else {
                console.error('Invalid response format');
            }
        },
        error: function(xhr, status, error) {
            console.error('Error fetching data: ' + error);
        }
    });
}

// 绑定点击事件
$(function() {
    $('#update').on('click', function() {
        updateChart($(this));
    });
});