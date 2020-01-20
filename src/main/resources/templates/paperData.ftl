<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">
    <base href="/">

    <title>创新方法数据</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="创新方法数据">

    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <#include "common/sidebar.ftl">
    </nav>

    <div id="page-wrapper" class="gray-bg dashbard-1">
        <#include "common/navbar.ftl">
        <div class="row  border-bottom white-bg dashboard-header">
            <div class="col-sm-3">
                <h2>Hello,超级管理员</h2>
                <small>您有32条消息和6个待处理事项</small>
                <ul class="list-group clear-list m-t">
                    <li class="list-group-item fist-item">
                            <span class="pull-right">
                                    ${pCnt}
                                </span>
                        <span class="label label-success">1</span>期刊论文
                    </li>
                    <li class="list-group-item">
                            <span class="pull-right">
                                    ${tCnt}
                                </span>
                        <span class="label label-info">2</span> 学位论文
                    </li>
                    <li class="list-group-item">
                            <span class="pull-right">
                                     ${cCnt}
                                </span>
                        <span class="label label-primary">3</span> 会议论文
                    </li>
                    <li class="list-group-item">
                            <span class="pull-right">
                                        ${oCnt}
                                </span>
                        <span class="label label-default">4</span> 其它论文
                    </li>

                </ul>
            </div>
            <div class="col-sm-6">
                <div class="flot-chart dashboard-chart">
                    <div class="flot-chart-content" id="flot-dashboard-chart"></div>
                </div>
                <div class="row text-left">
                    <div class="col-xs-4">
                    <div class=" m-l-md">
                    <span class="h4 font-bold m-t block">${all}</span>
                    <small class="text-muted m-b block">全部文献</small>
                    </div>
                    </div>
                    <div class="col-xs-4">
                    <span class="h4 font-bold m-t block">${has}</span>
                    <small class="text-muted m-b block">已收集文献</small>
                    </div>
                    <div class="col-xs-4">
                    <span class="h4 font-bold m-t block">${to}</span>
                    <small class="text-muted m-b block">未收集文献</small>
                    </div>

                </div>
            </div>
            <div class="col-sm-3">
                <div class="statistic-box">
                    <h4>
                        任务的进度
                    </h4>

                    <div class="row text-center">
                        <div class="col-lg-6">
                        <div class="chart1 easypiechart inline" data-percent="${pTask}"><span class="easypie-text">${pTask}%</span>
                        </div>
                        <h5>期刊任务</h5>
                        </div>
                        <div class="col-lg-6">
                        <div class="chart2 easypiechart inline" data-percent="${tTask}"><span class="easypie-text">${tTask}%</span>
                        </div>
                        <h5>学位论文任务</h5>
                        </div>
                        <div class="col-lg-6">
                        <div class="chart3 easypiechart inline" data-percent="${cTask}"><span class="easypie-text">${cTask}%</span>
                        </div>
                        <h5>会议任务</h5>
                        </div>
                        <div class="col-lg-6">
                        <div class="chart4 easypiechart inline" data-percent="${oTask}"><span class="easypie-text">${oTask}%</span>
                        </div>
                        <h5>其它任务</h5>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>订单</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="flot-chart" style="height: 500px;">
                                    <div class="flot-chart-content" id="flot-dashboard-chart2"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js?v=3.4.0"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.spline.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>

<!-- Peity -->
<script src="js/plugins/peity/jquery.peity.min.js"></script>
<script src="js/demo/peity-demo.js"></script>

<!-- Custom and plugin javascript -->
<script src="js/hplus.js?v=2.2.0"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI -->
<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- GITTER -->
<script src="js/plugins/gritter/jquery.gritter.min.js"></script>

<!-- EayPIE -->
<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

<!-- Sparkline -->
<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Sparkline demo data  -->
<script src="js/demo/sparkline-demo.js"></script>

<script src="js/plugins/echarts/echarts.js"></script>
<script>
    $(document).ready(function () {
        WinMove();
        setTimeout(function () {
            $.gritter.add({
                title: '您有2条未读信息',
                text: '请前往<a href="mailbox.html" class="text-warning">收件箱</a>查看今日任务',
                time: 10000
            });
        }, 2000);


        $('.chart1').easyPieChart({
            barColor: '#1c84c6',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        $('.chart2').easyPieChart({
            barColor: '#23c6c8',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        $('.chart3').easyPieChart({
            barColor: '#1ab394',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        $('.chart4').easyPieChart({
            barColor: '#d1dade',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        var tData = ${tTime};
        var pData = ${pTime};
        var oData = ${oTime};
        var cData = ${cTime};


        $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
            tData, pData,oData,cData
        ], {
            series: {
                lines: {
                    show: false,
                    fill: true
                },
                splines: {
                    show: true,
                    tension: 0.4,
                    lineWidth: 1,
                    fill: 0.4
                },
                points: {
                    radius: 0,
                    show: true
                },
                shadowSize: 2
            },
            grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#d5d5d5",
                borderWidth: 1,
                color: '#d5d5d5'
            },
            colors: ["#1ab394", "#464f88"],
            xaxis: {},
            yaxis: {
                ticks: 1
            },
            tooltip: false
        });

        //学科领域数据
        var domData = ${domData};

        var dataset = [
            {
                label: "数量",
                data: domData,
                color: "#1ab394",
                bars: {
                    show: true,
                    align: "center",
                    horizontal: true,
                    // barWidth: 0.8,
                    // lineWidth: 0
                }

            }
        ];


        var options = {
            xaxis: {
                position: "left",
                // max: 1070,
                color: "#838383",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Arial',
                axisLabelPadding: 3
            },
            yaxes: [{
                // mode: "time",
                ticks:${domTicks},
                // axisLabel: "Date",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Arial',
                axisLabelPadding: 10,
                color: "#838383"

            }
            ],
            legend: {
                noColumns: 1,
                labelBoxBorderColor: "#000000",
            },
            grid: {
                hoverable: false,
                borderWidth: 0,
                color: '#838383'
            }
        };
        $.plot($("#flot-dashboard-chart2"), dataset, options);

    });
</script>

</body>

</html>
