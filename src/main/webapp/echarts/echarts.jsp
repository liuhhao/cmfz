<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" language="java" %>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="smain" style="width: 500px;height:400px;"></div>

<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('smain'));
    var goEasy = new GoEasy({
        appkey: "BC-aa89e6a744874dab84688d9b524dfdae"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/selectRegisterCount',
                dataType: 'JSON',
                success: function (data) {
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '持明法州APP活跃用户',
                            subtext: ''
                        },
                        tooltip: {},
                        legend: {
                            data: ['数量']
                        },
                        xAxis: {
                            data: data.xAxisData
                        },
                        yAxis: {},
                        series: [{
                            name: '数量',
                            type: 'bar',
                            data: data.seriesData
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })
        }
    });
    $.ajax({
        type: 'post',
        url: '${pageContext.request.contextPath}/user/selectRegisterCount',
        dataType: 'JSON',
        success: function (data) {
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '持明法州APP活跃用户',
                    subtext: ''
                },
                tooltip: {},
                legend: {
                    data: ['数量']
                },
                xAxis: {
                    data: data.xAxisData
                },
                yAxis: {},
                series: [{
                    name: '数量',
                    type: 'bar',
                    data: data.seriesData
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })
</script>
