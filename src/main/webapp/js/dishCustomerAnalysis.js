const chartDom = $('#main');
const myChart = echarts.init(chartDom[0]);

let option;

option = {
    title: {
        text: 'Customer Distribution',
        subtext: 'Regarding Role,Gender and Age',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        left: 'center',
        top: 'bottom',
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
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    series: [
        {
            name: 'Gender Distribution',
            type: 'pie',
            radius: '25%',
            roseType: 'radius',
            center: ['20%', '50%'],
            data: [
            ]
        },
        {
            name: 'Age Distribution',
            type: 'pie',
            radius: '25%',
            roseType: 'radius',
            center: ['50%', '50%'],
            data: [
                // 年龄数据将通过Ajax填充
            ]
        },
        {
            name: 'Role Distribution',
            type: 'pie',
            radius: '25%',
            roseType: 'radius',
            center: ['80%', '50%'],
            data: []
        }
    ]

};

$(document).ready(function() {
    const dishId = $('#dishId').val();
    myChart.setOption(option);
    function fetchAndSetData(method, seriesIndex,startIndex) {
        return $.ajax({
            url:  path + "/jsp/dish",
            type: 'GET',
            data: {method: method ,dishId:dishId},
            dataType: 'json',
        }).done(function(data) {
            const seriesData = data.map(function (value, index) {
                return {value: value, name: option.legend.data[index + startIndex]};
            });
            myChart.setOption({
                series: [{
                    name: option.series[seriesIndex].name,
                    data: seriesData
                }]
            });
        }).fail(function(error) {
            console.error('Error fetching data for ' + method, error);
        });
    }

    // 并行获取所有数据
    $.when(
        fetchAndSetData('getGenderDistribution', 0,0),
        fetchAndSetData('getAgeDistribution', 1,2),
        fetchAndSetData('getRoleDistribution', 2,6)
    ).then(function() {
        // 所有数据都加载完成后的回调
        console.log('All data loaded and chart updated.');
    });
});
