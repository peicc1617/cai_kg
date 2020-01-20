<html>

<#--<head>-->

    <#--<meta charset="utf-8">-->
    <#--<meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">-->
    <#--<base href="/">-->

    <#--<title>创新方法知识图谱</title>-->
    <#--<meta name="keywords" content="">-->
    <#--<meta name="description" content="创新方法知识图谱">-->

    <#--<link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">-->

    <#--<!-- Morris &ndash;&gt;-->
    <#--<link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">-->

    <#--<!-- Gritter &ndash;&gt;-->
    <#--<link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">-->

    <#--<link href="css/animate.css" rel="stylesheet">-->
    <#--<link href="css/style.css?v=2.2.0" rel="stylesheet">-->

<#--</head>-->

<body>
<#--<div id="wrapper">-->
    <#--<nav class="navbar-default navbar-static-side" role="navigation">-->
        <#--<#include "common/sidebar.ftl">-->
    <#--</nav>-->

    <#--<div id="page-wrapper" class="gray-bg dashbard-1">-->
        <#--<#include "common/navbar.ftl">-->
        <#--<div class="row  border-bottom white-bg dashboard-header">-->
            <#--<div class="col-sm-3">-->
                <#--<h3>总数${all}</h3>-->
                <#--<h3>已完成${has}</h3>-->
                <#--<h3>未完成${to}</h3>-->
                <#--<ul class="list-group clear-list m-t">-->
                    <#--<li class="list-group-item fist-item">-->
                            <#--<span class="pull-right">-->
                                    <#--${pCnt}-->
                                <#--</span>-->
                        <#--<span class="label label-success">1</span> 期刊-->
                    <#--</li>-->
                    <#--<li class="list-group-item">-->
                            <#--<span class="pull-right">-->
                                    <#--${tCnt}-->
                                <#--</span>-->
                        <#--<span class="label label-info">2</span> 学位论文-->
                    <#--</li>-->
                    <#--<li class="list-group-item">-->
                            <#--<span class="pull-right">-->
                                    <#--${cCnt}-->
                                <#--</span>-->
                        <#--<span class="label label-primary">3</span> 会议-->
                    <#--</li>-->
                    <#--<li class="list-group-item">-->
                            <#--<span class="pull-right">-->
                                    <#--${oCnt}-->
                                <#--</span>-->
                        <#--<span class="label label-primary">3</span> 其它-->
                    <#--</li>-->
                <#--</ul>-->
            <#--</div>-->
            <#--<div class="col-sm-6">-->
                <#--<div class="flot-chart dashboard-chart">-->
                    <#--<div class="flot-chart-content" id="chart"></div>-->
                <#--</div>-->
                <#--<div class="row text-left">-->
                    <#--<div class="col-xs-4">-->
                        <#--<div class=" m-l-md">-->
                            <#--<span class="h4 font-bold m-t block">${all}</span>-->
                            <#--<small class="text-muted m-b block">全部文献</small>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="col-xs-4">-->
                        <#--<span class="h4 font-bold m-t block">${has}</span>-->
                        <#--<small class="text-muted m-b block">已收集文献</small>-->
                    <#--</div>-->
                    <#--<div class="col-xs-4">-->
                        <#--<span class="h4 font-bold m-t block">${to}</span>-->
                        <#--<small class="text-muted m-b block">未收集文献</small>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div class="col-sm-3">-->
                <#--<div class="statistic-box">-->
                    <#--<h4>-->
                        <#--文献爬取进度-->
                    <#--</h4>-->

                    <#--<div class="row text-center">-->
                        <#--<div class="col-lg-6">-->
                            <#--<div class="chart2 easypiechart inline" data-percent="${pTask}"><span class="easypie-text">${pTask}%</span>-->
                            <#--</div>-->
                            <#--<h5>期刊任务</h5>-->
                        <#--</div>-->
                        <#--<div class="col-lg-6">-->
                            <#--<div class="chart2 easypiechart inline" data-percent="${tTask}"><span class="easypie-text">${tTask}%</span>-->
                            <#--</div>-->
                            <#--<h5>学位论文任务</h5>-->
                        <#--</div>-->
                        <#--<div class="col-lg-6">-->
                            <#--<div class="chart2 easypiechart inline" data-percent="${cTask}"><span class="easypie-text">${cTask}%</span>-->
                            <#--</div>-->
                            <#--<h5>会议任务</h5>-->
                        <#--</div>-->
                        <#--<div class="col-lg-6">-->
                            <#--<div class="chart2 easypiechart inline" data-percent="${oTask}"><span class="easypie-text">${oTask}%</span>-->
                            <#--</div>-->
                            <#--<h5>其它任务</h5>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->

        <#--</div>-->
        <#--<div class="row">-->
            <#--<div class="col-lg-12">-->
                <#--<div class="ibox float-e-margins">-->
                    <#--<div class="ibox-title">-->
                        <#--<h5>学科领域分布<small> 通过输入关键字进行查询</small></h5>-->
                        <#--<div class="ibox-tools">-->
                            <#--<a class="collapse-link">-->
                                <#--<i class="fa fa-chevron-up"></i>-->
                            <#--</a>-->
                            <#--<a class="close-link">-->
                                <#--<i class="fa fa-times"></i>-->
                            <#--</a>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="ibox-content">-->
                        <#--<form method="get" class="form-horizontal">-->

                            <#--<div class="form-group">-->
                                <#--<div class="input-group">-->
                                    <#--<input type="text" name="search" class="form-control input-lg">-->
                                    <#--<div class="input-group-btn">-->
                                        <#--<button class="btn btn-lg btn-primary" type="submit">-->
                                            <#--搜索-->
                                        <#--</button>-->
                                        <#--<a type="button" class="btn btn-lg btn-success" href="/kgSearch.html">高级搜索</a>-->
                                    <#--</div>-->
                                <#--</div>-->

                            <#--</div>-->
                        <#--</form>-->

                        <#--<div>-->

                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<!-- Mainly scripts -->
<#--<script src="js/jquery-2.1.1.min.js"></script>-->
<#--<script src="js/bootstrap.min.js?v=3.4.0"></script>-->
<#--<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>-->
<#--<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>-->


<#--<!-- jQuery UI &ndash;&gt;-->
<#--<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>-->


<#--<!-- EayPIE &ndash;&gt;-->
<#--<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>-->

<#--<!-- jQuery UI &ndash;&gt;-->
<#--<script src="js/plugins/echarts/echarts.js"></script>-->
<#--<script>-->
    <#--$(document).ready(function () {-->
        <#--// WinMove();-->



        <#--$('.chart2').easyPieChart({-->
            <#--barColor: '#1c84c6',-->
            <#--//                scaleColor: false,-->
            <#--scaleLength: 5,-->
            <#--lineWidth: 4,-->
            <#--size: 80-->
        <#--});-->


        <#--var dom = document.getElementById("chart");-->
        <#--var myChart = echarts.init(dom);-->
        <#--var app = {};-->
        <#--option = null;-->
        <#--var base = +new Date(1968, 9, 3);-->
        <#--var oneDay = 24 * 3600 * 1000;-->
        <#--var date = [];-->

        <#--var data = ${tTime};-->

        <#--for (var i = 1; i < 20000; i++) {-->
            <#--var now = new Date(base += oneDay);-->
            <#--date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));-->
        <#--}-->

        <#--option = {-->
            <#--tooltip: {-->
                <#--trigger: 'axis',-->
                <#--position: function (pt) {-->
                    <#--return [pt[0], '10%'];-->
                <#--}-->
            <#--},-->
            <#--title: {-->
                <#--left: 'center',-->
                <#--text: '大数据量面积图',-->
            <#--},-->
            <#--toolbox: {-->
                <#--feature: {-->
                    <#--dataZoom: {-->
                        <#--yAxisIndex: 'none'-->
                    <#--},-->
                    <#--restore: {},-->
                    <#--saveAsImage: {}-->
                <#--}-->
            <#--},-->
            <#--xAxis: {-->
                <#--type: 'category',-->
                <#--boundaryGap: false,-->
                <#--data: date-->
            <#--},-->
            <#--yAxis: {-->
                <#--type: 'value',-->
                <#--boundaryGap: [0, '100%']-->
            <#--},-->
            <#--dataZoom: [{-->
                <#--type: 'inside',-->
                <#--start: 0,-->
                <#--end: 10-->
            <#--}, {-->
                <#--start: 0,-->
                <#--end: 10,-->
                <#--handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',-->
                <#--handleSize: '80%',-->
                <#--handleStyle: {-->
                    <#--color: '#fff',-->
                    <#--shadowBlur: 3,-->
                    <#--shadowColor: 'rgba(0, 0, 0, 0.6)',-->
                    <#--shadowOffsetX: 2,-->
                    <#--shadowOffsetY: 2-->
                <#--}-->
            <#--}],-->
            <#--series: [-->
                <#--{-->
                    <#--name:'模拟数据',-->
                    <#--type:'line',-->
                    <#--smooth:true,-->
                    <#--symbol: 'none',-->
                    <#--sampling: 'average',-->
                    <#--itemStyle: {-->
                        <#--color: 'rgb(255, 70, 131)'-->
                    <#--},-->
                    <#--areaStyle: {-->
                        <#--color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{-->
                            <#--offset: 0,-->
                            <#--color: 'rgb(255, 158, 68)'-->
                        <#--}, {-->
                            <#--offset: 1,-->
                            <#--color: 'rgb(255, 70, 131)'-->
                        <#--}])-->
                    <#--},-->
                    <#--data: data-->
                <#--}-->
            <#--]-->
        <#--};-->
    <#--});-->
<#--</script>-->


</body>

</html>
