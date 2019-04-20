<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<table id="dg_user"></table>
<script>
    var tb = [{
        iconCls: 'icon-add',
        text: '添加',
        handler: function () {

        }
    }, '-', {
        iconCls: 'icon-edit',
        text: '修改',
        handler: function () {
        }
    }, '-', {
        iconCls: 'icon-delete',
        text: '删除',
        handler: function () {
        }
    }, '-', {
        iconCls: 'icon-save',
        text: '保存',
        handler: function () {
        }
    }, '-', {
        iconCls: 'icon-back',
        text: '导出',
        handler: function () {
        }
    }];
    $('#dg_user').edatagrid({
        method: "post",
        url: "${pageContext.request.contextPath}/user/selectAll",
        toolbar: tb,
        fit: true,
        pagination: true,
        pageSize: 4,
        pageList: [4, 6, 8],
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'dharma', title: '法名', width: 100},
            {field: 'sex', title: '性别(0是女/1是男)', width: 100},
            {field: 'province', title: '省份', width: 100},
            {field: 'city', title: '市', width: 100},
            {field: 'sign', title: '签名', width: 100},
            {field: 'status', title: '状态(0是正常/1是被封)', width: 100, editor: {type: "text", options: {precision: 1}}},
            {field: 'phone', title: '电话', width: 150},
            {field: 'password', title: '密码', width: 100},
            {field: 'salt', title: '盐值', width: 150},
            {field: 'headImg', title: '头像图片地址', width: 150},
            {field: 'createDate', title: '创建时间', width: 150},
            {field: 'masterId', title: '上师id', width: 150},
        ]],
        view: detailview,
        detailFormatter: function (rowIndex, rowData) {
            return '<table><tr>' +
                '<td rowspan=6 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.headImg + '" style="height:150px;width:100px"></td>' +
                '<td style="border:0">' +
                '<p>姓名: ' + rowData.name + '</p>' +
                '<p>法名: ' + rowData.dharma + '</p>' +
                '<p>性别: ' + rowData.sex + '</p>' +
                '<p>住址: ' + rowData.city + '  ' + rowData.province + '</p>' +
                '<p>签名: ' + rowData.sign + '</p>' +
                '<p>状态: ' + rowData.status + '</p>' +
                '</td>' +
                '</tr></table>';
        }
    });
</script>