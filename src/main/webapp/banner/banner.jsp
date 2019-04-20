<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<table id="dg_banner"></table>
<div id="dd_banner" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        addBanner();
                },{
                    text:'关闭',
                    handler:function(){
                        closeBanner();
                    }
                }]">

    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">标题:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        图片：<input class="easyui-filebox" name="file1" style="width:150px">
    </form>
</div>
<script>
    var tb = [{
        iconCls: 'icon-add',
        text: '添加',
        handler: function () {
            $('#dd_banner').dialog('open');

        }
    }, '-', {
        iconCls: 'icon-edit',
        text: '修改',
        handler: function () {
            $('#dg_banner').edatagrid('saveRow');
        }
    }, '-', {
        iconCls: 'icon-delete',
        text: '删除',
        handler: function () {
            $('#dg_banner').edatagrid('destroyRow');
        }
    }, '-', {
        iconCls: 'icon-save',
        text: '保存',
        handler: function () {
            $('#dg_banner').edatagrid('saveRow');
        }
    }, '-', {
        iconCls: 'icon-back',
        text: '导出',
        handler: function () {
            window.location = "${pageContext.request.contextPath}/banner/export"
        }
    }];
    $('#dg_banner').edatagrid({
        method: "get",
        url: "${pageContext.request.contextPath}/banner/selectAll.do",
        saveUrl: "${pageContext.request.contextPath}/banner/updateStatus.do",
        updateUrl: "${pageContext.request.contextPath}/banner/updateStatus.do",
        destroyUrl: "${pageContext.request.contextPath}/banner/delete.do",
        toolbar: tb,
        fit: true,
        pagination: true,
        pageSize: 4,
        pageList: [4, 6, 8],
        columns: [[
            {field: 'title', title: '标题', width: 200},
            {field: 'status', title: '状态(0是不激活/1是激活)', width: 200, editor: {type: "text", options: {precision: 1}}},
            {field: 'imgPath', title: '路径', width: 200},
            {field: 'createDate', title: '时间', width: 200}
        ]],
        view: detailview,
        detailFormatter: function (rowIndex, rowData) {
            return '<table><tr>' +
                '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.imgPath + '" style="height:50px;"></td>' +
                '<td style="border:0">' +
                '<p>标题: ' + rowData.title + '</p>' +
                '<p>状态: ' + rowData.status + '</p>' +
                '</td>' +
                '</tr></table>';
        }
    });

    function addBanner() {
        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/banner/insert',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $("#dd_banner").dialog("close");
                    $('#dg_banner').datagrid("reload");
                }
            }
        });
    }

    function closeBanner() {
        $("#dd_banner").dialog("close")
    }
</script>
