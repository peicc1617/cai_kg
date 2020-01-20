<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <base href="/">
    <title>创新方法知识图谱-数据标注</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="创新方法知识图谱-高级搜索">

    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">
    <script src="https://d3js.org/d3.v5.min.js"></script>


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
                        <h5>基本</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_basic.html#">选项1</a>
                                </li>
                                <li><a href="table_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        当前已经完成${finished}，当前未完成${unfinished}。

                        <a class="btn btn-primary pull-right" href="/textData/anno/unfinished" target="_blank">下载未标注的数据</a>
                        <a class="btn btn-success pull-right" href="/textData/anno/finished" target="_blank">下载已标注的数据</a>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>是否完成</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list textList as text>
                                <tr>
                                    <td><a href="/textData/${text.id?string('0')}.html">${text.id}</a></td>
                                    <#if text.state>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    <#else >
                                        <td><a href="table_basic.html#"><i class="fa fa-question text-warning"></i></a>
                                        </td>
                                    </#if>
                                </tr>

                            </#list>
                            </tbody>
                        </table>

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

    });



</script>


</body>

</html>
