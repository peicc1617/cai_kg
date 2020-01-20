<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>创新方法知识图谱-高级搜索</title>
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
                        <h5>需求匹配
                            <small class="m-l-sm">需求定义</small>
                        </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="tabs_panels.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12 ">
                                <div class="col-sm-4">
                                    <div class="input-group m-b"><span class="input-group-addon" onclick="inputTest()">企业</span>
                                        <input type="text" placeholder="请输入企业名称" class="form-control demand-map" name="ORG">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input-group m-b"><span class="input-group-addon">产品</span>
                                        <input type="text" placeholder="请输入企业名称" class="form-control demand-map" name="PRO">
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                    <div class="input-group m-b"><span class="input-group-addon" >模板</span>
                                        <select class="form-control demand-map" id="form-field-select-1" name="IMC">
                                            <option value=""></option>
                                            <option value="DMAIC">DMAIC</option>
                                            <option value="DMADV">DMADV</option>
                                            <option value="价值流">价值流</option>
                                            <option value="TRIZ">TRIZ</option>
                                            <option value="5S">5S</option>
                                            <option value="价值工程">价值工程</option>

                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="input-group m-b"><span class="input-group-addon">生产难题描述</span>
                                    <input type="text" placeholder="请描述生产难题" class="form-control demand-map" name="EPRO">
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="input-group m-b"><span class="input-group-addon">阶段需求描述</span>
                                    <input type="text" placeholder="请描述阶段需求" class="form-control demand-map" name="DMD">
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="input-group m-b"><span class="input-group-addon">基础创新方法历史集合</span>
                                    <select multiple="" name="IMM" class="chosen-select form-control demand-map" id="form-field-select-4"
                                            data-placeholder="Choose a State...">
                                        <option value="SIPOC">SIPOC</option>
                                        <option value="Pareto图">Pareto图</option>
                                        <option value="鱼骨图">鱼骨图</option>
                                        <option value="生产线平衡">生产线平衡</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" onclick="initDemandMap()">需求子图预览</button>
                                    <button class="btn btn-success">搜索</button>
                                    <button class="btn btn-warning">重置</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel blank-panel">

                    <div class="panel-heading">
                        <div class="panel-options">

                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="tabs_panels.html#tab-1"
                                                      aria-expanded="true">需求子图预览</a>
                                </li>
                                <li class=""><a data-toggle="tab" href="tabs_panels.html#tab-2" aria-expanded="false">搜索结果</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="panel-body">

                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <#--<div id="container" style="height: 400%"></div>-->
                                <svg id="data-map" width="800" height="500"></svg>
                            </div>

                            <div id="tab-2" class="tab-pane">


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

    class DataMap {
        constructor(data) {
            this.svg = d3.select("#data-map");
            // this.linkG = this.svg.append("g")
            //     .attr("stroke", "#999")
            //     .attr("stroke-opacity", 0.6);
            // this.nodeG = this.svg.append("g")
            //     .attr("stroke", "#fff")
            //     .attr("stroke-width", 1.5);
            this.links = data.links.map(d => Object.create(d));
            this.nodes = data.nodes.map(d => Object.create(d));
            this.height = 600;
            this.width = $("#data-map").parent().width();
            this.svg.attr("height",this.height);
            this.svg.attr("width",this.width);
            this.simulation = d3.forceSimulation(this.nodes)
                .force("link", d3.forceLink(this.links).id(d => d.id).distance(d => 200))
                .force("charge", d3.forceManyBody())
                .force("center", d3.forceCenter(this.width / 2, this.height / 2));
            // this.refreshSVG();
        }

        refreshSVG() {
            const simulation = this.simulation;
            // this.simulation.stop();
            this.simulation.nodes(this.nodes);
            const scale = d3.scaleOrdinal(d3.schemeCategory10);
            d3.forceCenter([0,0])
            const drag = simulation => {
                function dragstarted(d) {
                    if (!d3.event.active) simulation.alphaTarget(0.3).restart();
                    d.fx = d.x;
                    d.fy = d.y;
                }

                function dragged(d) {
                    d.fx = d3.event.x;
                    d.fy = d3.event.y;
                }

                function dragended(d) {
                    if (!d3.event.active) simulation.alphaTarget(0);
                    d.fx = null;
                    d.fy = null;
                }
                return d3.drag()
                    .on("start", dragstarted)
                    .on("drag", dragged)
                    .on("end", dragended);
            }


            const link = this.svg.append("g")
                .attr("stroke", "#999")
                .attr("stroke-opacity", 0.6)
                .selectAll("line")
                .data(this.links)
                .join("line")
                .attr("stroke-width", d => Math.sqrt(d.value));

            const node = this.svg.append("g")
                .attr("stroke-width", 1.5)
                .attr("fill", "#fff")
                .selectAll("circle")
                .data(this.nodes)
                .join("circle")
                .attr("r", 15)
                .attr("stroke",d => scale(d.group))
                .call(drag(simulation));


            node.append("title")
                .text(d =>
                    d.name);

            const nodeText = this.svg.append("g")
                .selectAll("text")
                .data(this.nodes)
                .join("text")

            const linkText = this.svg.append("g")
                .selectAll("text")
                .data(this.links)
                .join("text")


            const tickFunction = function () {
                link
                    .attr("x1",
                        d => d.source.x
                    )
                    .attr("y1", d => d.source.y)
                    .attr("x2", d => d.target.x)
                    .attr("y2", d => d.target.y);

                linkText .attr("x", d => (d.target.x+d.source.x)/2)
                    .attr("y", d =>  (d.target.y+d.source.y)/2)
                    .attr("transform",d=>{
                        return "rotate("+ Math.atan2((d.target.y-d.source.y), (d.target.x-d.source.x))*(180/Math.PI)+","+ (d.target.x+d.source.x)/2+","+(d.target.y+d.source.y)/2+")"
                    })
                    .attr("fill","grey")
                    .attr("font-size","10")
                    .attr("text-anchor","middle")
                    .attr("dominant-baseline","ideographic")
                    .text(d=>d.relation);
                node
                    .attr("cx", d => d.x)
                    .attr("cy", d => d.y)

                nodeText.attr("x", d => d.x)
                    .attr("y", d => d.y+20)
                    .attr("text-anchor","middle")
                    .attr("dominant-baseline","hanging")
                    .text(d=>d.name);

            }


            this.simulation.on("tick",tickFunction);
            // this.simulation.on("tick", function () { console.log("run...") });
            // this.simulation.on("end", tickFunction);
            // simulation.alphaTarget(0.3).restart()
        }

        // addData(data){
        //     data.nodes.forEach(node=>{
        //         this.nodes.push(Object.create(node))
        //     })
        //     data.links.forEach(link=>{
        //         this.links.push(Object.create(link))

        //     })
        //     this.refreshSVG();
        // }
        // tick(){
        //     this.simulation.tick();
        // }
        // addDataTest(){
        //     this.nodes.push(Object.create({"id": "123", "group": 1}))
        //     this.nodes.push(Object.create({"id": "12311", "group": 1}))
        //     this.links.push(Object.create({"source": "123", "target": "12311", "value": 20}))
        //     this.refreshSVG();
        //     this.tick();
        // }

    }
    // var data = {
    //     "nodes": [
    //         {
    //             "id": "冰箱制造企业",
    //             "group": 0
    //         },
    //         {
    //             "id": "冰箱",
    //             "group": 1
    //         },
    //         {
    //             "id": "DMAIC模板",
    //             "group": 2
    //         },
    //         {
    //             "id": "工序质量问题",
    //             "group": 3
    //         },
    //         {
    //             "id": "问题的原因",
    //             "group": 4
    //         },
    //         {
    //             "id": "SIPOC",
    //             "group": 5
    //         },
    //         {
    //             "id": "基础创新方法",
    //             "group": 6
    //         }
    //     ],
    //     "links": [
    //         {
    //             "source": "冰箱制造企业",
    //             "target": "冰箱",
    //             "value": 1,
    //             "relation":"Have"
    //         },
    //         {
    //             "source": "冰箱",
    //             "target": "工序质量问题",
    //             "value": 1,
    //             "relation":"About"
    //         },
    //         {
    //             "source": "冰箱制造企业",
    //             "target": "工序质量问题",
    //             "value": 1,
    //             "relation":"Have"
    //
    //         },
    //         {
    //             "source": "工序质量问题",
    //             "target": "问题的原因",
    //             "value": 1,
    //             "relation":"Include"
    //
    //         },
    //         {
    //             "source": "DMAIC模板",
    //             "target": "基础创新方法",
    //             "value": 1,
    //             "relation":"Nest"
    //
    //         },
    //         {
    //             "source": "SIPOC",
    //             "target": "基础创新方法",
    //             "value": 1,
    //             "relation":"Pre"
    //
    //         },
    //         {
    //             "source": "SIPOC",
    //             "target": "基础创新方法",
    //             "value": 1,
    //             "relation":"Post"
    //
    //         },
    //         {
    //             "source": "基础创新方法",
    //             "target": "问题的原因",
    //             "value": 1,
    //             "relation":"Solve"
    //         },
    //         {
    //             "source": "基础创新方法",
    //             "target": "工序质量问题",
    //             "value": 1,
    //             "relation":"Solve"
    //         }
    //
    //     ]
    // };

    function initDemandMap(){
        console.log("生成需求子图")
        const data = {
            nodes:[],
            links:[]
        }
        const typeMap = new Map();
        let nodeID = 0;
        $(".demand-map").each((i,el)=>{
            let val = $(el).val();
            if(val==null){
                return
            }
            if(typeof val =="string" || val.length==undefined){
                val=[val];
            }
            val.forEach(e=>{
                const node = {
                    "id": nodeID++,
                    "name": e,
                    "group": i,
                    "type":el.name
                }
                data.nodes.push(node)
                if(typeMap.has(el.name)){
                    typeMap.get(el.name).push(node)
                }else{
                    typeMap.set(el.name,[node])
                }
            })

        })
        let createLinfFunction = (type1,type2,relation)=>{
            if(typeMap.has(type1)){
                typeMap.get(type1).forEach(node1=>{
                    if(typeMap.has(type2)){
                        typeMap.get(type2).forEach(node2=>{
                            if(node1.id!=node2.id){
                                data.links.push({
                                    "source": node1.id,
                                    "target": node2.id,
                                    "value": 1,
                                    "relation":relation
                                })
                            }

                        })
                    }
                })
            }
        }
        createLinfFunction("ORG","PRO","O_HAVE_P");
        createLinfFunction("PRO","EPRO","P_HAVE_E");
        createLinfFunction("ORG","EPRO","O_HAVE_E");
        createLinfFunction("EPRO","DMD","E_HAVE_D");
        createLinfFunction("IMC","IMM","NEST");
        createLinfFunction("IMM","IMM","PRE");
        createLinfFunction("IMM","IMM","POST");
        createLinfFunction("IMM","EPRO","M_SOLVE_E");
        createLinfFunction("IMM","DMD","M_SOLVE_D");

        var demandMap = new DataMap(data);

        demandMap.refreshSVG();
    }

    function inputTest() {
        let testData=  {}
        if(Math.random()>0.5){
            testData={
                ORG:"空调制造企业",
                PRO:"空调",
                IMC:"VSM",
                EPRO:"生产线效率",
                DMD:"消除浪费",
                IMM:"Pareto图"
            }
        }else {
            testData={
                ORG:"冰箱制造企业",
                PRO:"冰箱",
                IMC:"DMAIC",
                EPRO:"发泡工序质量问题",
                DMD:"全面质量管理",
                IMM:"SIPOC"
            }
        }
        $('.demand-map[name=ORG]').val(testData.ORG)
        $('.demand-map[name=PRO]').val(testData.PRO)
        $('.demand-map[name=IMC]').val(testData.IMC)
        $('.demand-map[name=EPRO]').val(testData.EPRO)
        $('.demand-map[name=DMD]').val(testData.DMD)
        $('.demand-map[name=IMM]').val(testData.IMM)


    }
    // function initDemandMap() {
    //     var dom = document.getElementById("container");
    //     var myChart = echarts.init(dom);
    //     var app = {};
    //     option = null;
    //     myChart.showLoading();
    //     $.getJSON('data/asset/data/npmdepgraph.min10.json', function (json) {
    //         myChart.hideLoading();
    //         myChart.setOption(option = {
    //             title: {
    //                 text: 'NPM Dependencies'
    //             },
    //             animationDurationUpdate: 1500,
    //             animationEasingUpdate: 'quinticInOut',
    //             series : [
    //                 {
    //                     type: 'graph',
    //                     layout: 'none',
    //                     // progressiveThreshold: 700,
    //                     data: json.nodes.map(function (node) {
    //                         return {
    //                             x: node.x,
    //                             y: node.y,
    //                             id: node.id,
    //                             name: node.label,
    //                             symbolSize: node.size,
    //                             itemStyle: {
    //                                 normal: {
    //                                     color: node.color
    //                                 }
    //                             }
    //                         };
    //                     }),
    //                     edges: json.edges.map(function (edge) {
    //                         return {
    //                             source: edge.sourceID,
    //                             target: edge.targetID
    //                         };
    //                     }),
    //                     label: {
    //                         emphasis: {
    //                             position: 'right',
    //                             show: true
    //                         }
    //                     },
    //                     roam: true,
    //                     focusNodeAdjacency: true,
    //                     itemStyle: {
    //                         normal: {
    //                             borderColor: '#fff',
    //                             borderWidth: 1,
    //                             shadowBlur: 10,
    //                             shadowColor: 'rgba(0, 0, 0, 0.3)'
    //                         }
    //                     },
    //
    //                     lineStyle: {
    //                         color: 'source',
    //                         curveness: 0.3
    //                     },
    //                     emphasis: {
    //                         lineStyle: {
    //                             width: 10
    //                         }
    //                     }
    //                 }
    //             ]
    //         }, true);
    //     });;
    //     if (option && typeof option === "object") {
    //         myChart.setOption(option, true);
    //     }
    // }

</script>


</body>

</html>
