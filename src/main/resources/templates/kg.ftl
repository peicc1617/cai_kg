<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>创新方法知识图谱</title>
    <meta name="keywords" content="">
    <meta name="description" content="创新方法知识图谱">

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
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>知识图谱信息
                            <small> 统计知识图谱内容</small>
                        </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row  border-bottom white-bg dashboard-header">
                            <div class="col-sm-3">
                                <h2>Hello,超级管理员</h2>
                                <small>您有32条消息和6个待处理事项</small>
                                <ul class="list-group clear-list m-t">
                                    <li class="list-group-item fist-item">
                            <span class="pull-right">
                                ${cnt.IM}
                                </span>
                                        <span class="label label-success">1</span> 创新方法概念
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.IMM}
                                </span>
                                        <span class="label label-success">1</span> 构件型基础创新方法
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                      ${cnt.IMC}
                                </span>
                                        <span class="label label-success">2</span> 容器型基础创新方法
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.EPRO}
                                </span>
                                        <span class="label label-success">3</span> 生产难题
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                        ${cnt.DMD}
                                </span>
                                        <span class="label label-success">4</span> 创新需求
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.ORG}
                                </span>
                                        <span class="label label-success">5</span> 企业
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.PRO}
                                </span>
                                        <span class="label label-success">6</span> 产品
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.ACT}
                                </span>
                                        <span class="label label-success">7</span> 创新活动
                                    </li>
                                </ul>
                            </div>
                            <div class="col-sm-3">
                                <div class="flot-chart dashboard-chart">
                                    <div class="flot-chart-content" id="flot-dashboard-chart"></div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-xs-6">
                                        <div class=" m-l-md">
                                            <span class="h4 font-bold m-t block">${cnt.eCNT}</span>
                                            <small class="text-muted m-b block">实体总量</small>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <span class="h4 font-bold m-t block">${cnt.rCNT}</span>
                                        <small class="text-muted m-b block">关系总量</small>
                                    </div>


                                </div>
                            </div>
                            <div class="col-sm-3">
                                <ul class="list-group clear-list m-t">
                                    <li class="list-group-item fist-item">
                            <span class="pull-right">
                                ${cnt.MIX}
                                </span>
                                        <span class="label label-primary">1</span> 嵌套
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.PRE}
                                </span>
                                        <span class="label label-primary">1</span> 前序
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                      ${cnt.POST}
                                </span>
                                        <span class="label label-primary">2</span> 后序
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.MIX}
                                </span>
                                        <span class="label label-primary">3</span> 融合
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                        ${cnt.M_SOLVE_E}
                                </span>
                                        <span class="label label-primary">4</span> 方法解决难题
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.M_SOLVE_D}
                                </span>
                                        <span class="label label-primary">5</span> 方法满足需求
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.O_HAVE_P}
                                </span>
                                        <span class="label label-primary">6</span> 组织拥有产品
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.O_DEVELOP_A}
                                </span>
                                        <span class="label label-primary">7</span> 组织开展活动
                                    </li>
                                    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.O_HAVE_E}
                                </span>
                                        <span class="label label-primary">8</span> 组织存在难题
                                    </li>

                                </ul>
                            </div>
                            <div class="col-sm-3">
                                <ul class="list-group clear-list m-t">
                                  <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.O_USE_M}
                                </span>
                                        <span class="label label-primary">9</span> 组织使用方法
                                    </li>    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.O_HAVE_D}
                                </span>
                                        <span class="label label-primary">10</span> 组织存在需求
                                    </li>    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.A_SOLVE_E}
                                </span>
                                        <span class="label label-primary">11</span> 活动解决难题
                                    </li>    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.A_SOLVE_D}
                                </span>
                                        <span class="label label-primary">12</span> 活动解决需求
                                    </li>    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.A_USE_M}
                                </span>
                                        <span class="label label-primary">13</span> 活动使用方法
                                    </li>    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.P_HAVE_E}
                                </span>
                                        <span class="label label-primary">14</span> 产品存在难题
                                    </li>    <li class="list-group-item">
                            <span class="pull-right">
                                       ${cnt.P_HAVE_D}
                                </span>
                                        <span class="label label-primary">15</span> 产品存在需求
                                    </li>

                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>知识图谱查询
                            <small> 通过输入关键字进行查询</small>
                        </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text" name="search" class="form-control input-lg">
                                    <div class="input-group-btn">
                                        <button class="btn btn-lg btn-primary" type="submit">
                                            搜索
                                        </button>
                                        <a type="button" class="btn btn-lg btn-success" href="/kgSearch.html">高级搜索</a>
                                    </div>
                                </div>

                            </div>
                        </form>
                        <div>
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


        $('.chart').easyPieChart({
            barColor: '#f8ac59',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        $('.chart2').easyPieChart({
            barColor: '#1c84c6',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        var data1 = [
            [0, 4], [1, 8], [2, 5], [3, 10], [4, 4], [5, 16], [6, 5], [7, 11], [8, 6], [9, 11], [10, 30], [11, 10], [12, 13], [13, 4], [14, 3], [15, 3], [16, 6]
        ];
        var data2 = [
            [0, 1], [1, 0], [2, 2], [3, 0], [4, 1], [5, 3], [6, 1], [7, 5], [8, 2], [9, 3], [10, 2], [11, 1], [12, 0], [13, 2], [14, 8], [15, 0], [16, 0]
        ];
        $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
            data1, data2
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
                ticks: 4
            },
            tooltip: false
        });
    });
</script>


</body>

</html>
