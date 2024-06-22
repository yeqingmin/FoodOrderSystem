const app = {};
const chartDom = $('#main');
const myChart = echarts.init(chartDom[0]);
let option;

const posList = [
    'left',
    'right',
    'top',
    'bottom',
    'inside',
    'insideTop',
    'insideLeft',
    'insideRight',
    'insideBottom',
    'insideTopLeft',
    'insideTopRight',
    'insideBottomLeft',
    'insideBottomRight'
];
app.configParameters = {
    rotate: {
        min: -90,
        max: 90
    },
    align: {
        options: {
            left: 'left',
            center: 'center',
            right: 'right'
        }
    },
    verticalAlign: {
        options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
        }
    },
    position: {
        options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
        }, {})
    },
    distance: {
        min: 0,
        max: 100
    }
};
app.config = {
    rotate: 90,
    align: 'left',
    verticalAlign: 'middle',
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
        const labelOption = {
            rotate: app.config.rotate,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            position: app.config.position,
            distance: app.config.distance
        };
        myChart.setOption({
            series: [
                {
                    label: labelOption
                },
                {
                    label: labelOption
                },
                {
                    label: labelOption
                },
                {
                    label: labelOption
                }
            ]
        });
    }
};
const labelOption = {
    show: true,
    position: app.config.position,
    distance: app.config.distance,
    align: app.config.align,
    verticalAlign: app.config.verticalAlign,
    rotate: app.config.rotate,
    formatter: '{c}  {name|{a}}',
    fontSize: 16,
    rich: {
        name: {}
    }
};
option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['Comment Amount', 'Rating']
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar', 'stack'] },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },
    xAxis: [
        {
            type: 'category',
            axisTick: { show: false },
            data: [
                'Male',
                'Female',
                'age<20',
                '20<age<40',
                '40<age<60',
                'age>60',
                'Teacher',
                'Student'
            ]
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: 'Comment Amount',
            type: 'bar',
            barGap: 0,
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: []
        },
        {
            name: 'Rating',
            type: 'bar',
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: []
        }
    ]
};

$(document).ready(function() {
    myChart.setOption(option);
    function fetchDataAndUpdateChart() {
        $.ajax({
            url:  path + "/jsp/merchant",
            type: 'GET',
            data: {method: "getData"},
            dataType: 'json',
            success: function(data) {
                // 假设返回的数据格式是 {commentAmount: [/* 数组 */], rating: [/* 数组 */] }
                if (data && data.commentAmount && data.rating) {
                    const commentAmountData = data.commentAmount;
                    const ratingData = data.rating;

                    // 更新 ECharts 的 series 数据
                    option.series[0].data = commentAmountData;
                    option.series[1].data = ratingData;

                    // 应用新的配置项和数据
                    myChart.setOption(option);
                } else {
                    console.error('Invalid data format');
                }
            },
            error: function(xhr, status, error) {
                console.error('Error fetching data', error);
            }
        });
    }

    fetchDataAndUpdateChart();

});
