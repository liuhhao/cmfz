<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',height:100,title:'上',split:true">北</div>
<div data-options="region:'south',height:100,title:'南'">南</div>
<div data-options="region:'west',width:200,title:'zuo'">
    <div class="easyui-accordion" data-options="fit:true">
        <div data-options="title:'用户管理',iconCls:'icon-reload'">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="toAddTabs()">展示用户信息</a>
        </div>
        <div data-options="title:'分类管理',iconCls:'icon-search'">
            <ul id="checkForm"></ul>
        </div>
        <div data-options="title:'XX管理',iconCls:'icon-search'"></div>
        <div data-options="title:'xxxx',iconCls:'icon-search'"></div>
    </div>
</div>
<div data-options="region:'east',width:100,title:'东边'">右边</div>
<div data-options="region:'center',title:'中'">
    <div id="myTabsDiv">
        <div data-options="title:'欢迎页',closable:true" style="font-size: 50px; margin-left:300px; margin-top: 100px">
            欢迎使用综合管理系统
        </div>
    </div>
</div>
</body>
<script>
    <!--为此标签添加一个选项卡-->
    $("#myTabsDiv").tabs({
        fit: true
    })

    //当点击用户信息时会进入这个方法
    function toAddTabs() {
        var isExists = $("#myTabsDiv").tabs("exists", "用户信息");
        if (isExists) {
            $("#myTabsDiv").tabs("select", "用户信息");
        } else {
            $("#myTabsDiv").tabs("add", {
                title: "用户信息",
                /*closable:true,*/
                /*selected:true,*/
                content: "<iframe src='UserList.jsp' width='100%' height='100%'></iframe>"
            })
        }
    }
</script>
</html>
