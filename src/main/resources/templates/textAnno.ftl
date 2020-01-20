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
    <#--<link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">-->

    <!-- Morris -->
    <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">
    <style>
        rect.block.hover{
            fill:red;
        }
    </style>
    <script src="https://d3js.org/d3.v5.min.js"></script>

</head>

<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <#include "common/sidebar.ftl">
    </nav>

    <div id="page-wrapper" class="gray-bg dashbard-1">
        <#include "common/navbar.ftl">
        <div class="hr-line-dashed"></div>
        <div class="hr-line-dashed"></div>

        <div class="row">
            <div class="col-lg-12">

                    <svg id="svg"></svg>
                <#--<div class="col-md-4">-->
                    <#--<h4 class="d-flex justify-content-between align-items-center mb-2">-->
                        <#--<span class="text-muted">标签属性</span>-->
                        <#--<span class="badge badge-secondary badge-pill">3</span>-->
                    <#--</h4>-->
                    <#--<ul id="tag-ul" class="list-group mb-4">-->
                    <#--</ul>-->

                    <#--<form class="card p-2">-->
                        <#--<div class="btn-group btn-group-sm " role="group" aria-label="...">-->
                        <#--</div>-->
                    <#--</form>-->
                <#--</div>-->
            </div>
        </div>
        <div class="footer fixed">
            <div class="pull-right">
                <a type="button" class="btn btn-info"  href="/textData/random">随机选择</a>
                <button type="button" class="btn btn-success "  onclick="save2Server()">保存到服务器</button>

            </div>
        </div>
    </div>

</div>

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js?v=3.4.0"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>


<!-- Custom and plugin javascript -->
<script src="js/hplus.js?v=2.2.0"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI -->
<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

<script src="myjs/tag.js"></script>


<script>
    const tag = new Tag()
    $.get("/textData/anno/${textID?string(0)}.json", function(result){
        tag.init(result.result);
    })
    $(document).ready(function () {
        WinMove();
    });
    // tag.init('随\r\n着\n社\tB-IMM\n会\tE-IMM\n经\n济\n的\n快\tB-IMM\n速\tM-IMM\n发\tM-IMM\n展\tE-IMM\n，\n企\n业\n与\n企\tS-IMM\n业\n之\n间\n的\n竞\n争\n日\n益\n加\n剧\n，\n有\n效\n探\n测\n顾\n客\n需\n求\n，\n为\n顾\n客\n提\n供\n卓\n越\n的\n顾\n客\n价\n值\n成\n为\n企\n业\n获\n得\n竞\n争\n优\n势\n的\n来\n源\n。')
    function save2Server() {
        const  str = tag.saveToText()
        $.post("/textData/anno", {
            id:${textID?string(0)},
            result:str,
            state:true
        },function(result){
            // window.location.reload()
        },function () {
            alert("更新失败")
                    })

    }

</script>


</body>

</html>
