<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<table id="tt_album" style="width:600px;height:400px"></table>
<div id="detailDialog">
    <form id="detailForm">
        专辑名称：<input type="text" name="title" readonly><br/>
        章节数量：<input type="text" name="amount" readonly><br/>
        专辑评分：<input type="text" name="score" readonly><br/>
        专辑作者：<input type="text" name="author" readonly><br/>
        专辑播音：<input type="text" name="boardcast" readonly><br/>
        上传时间：<input type="text" name="publishDate" readonly><br/>
        图片路径：<input type="text" name="imgPath" readonly><br/>
        专辑简介：<textarea name="brief" readonly style="height: 100px;width: 250px"></textarea>
    </form>
</div>
<div id="insertAlbumDiv">
    <form id="insertAlbumForm" enctype="multipart/form-data" method="post">
        标题:<input type="text" name="title" id="atitle"/><br>
        作者:<input type="text" name="author" id="aauthor"/><br>
        播音员:<input type="text" name="boardcast" id="aboardcast"/><br>
        简介:<input type="text" name="brief" id="abrief"/><br>
        图片:<input type="file" name="file1"/>
    </form>
</div>
<div id="insertAlbumBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="doInsertAlbum()">添加</a>
</div>
<div id="insertChapterDiv">
    <form id="insertChapterForm" enctype="multipart/form-data" method="post">
        <input type="hidden" name="cid" id="cid"/>
        标题:<input type="text" name="title" id="ctitle"/><br>
        内容:<input type="file" name="file1"/>
    </form>
</div>
<div id="insertChapterBtn">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doInsertChapter()">添加</a>
</div>
<script>
    var ss = "";
    $("#detailDialog").dialog({
        title: '专辑详情',
        width: 400,
        height: 200,
        closed: true,
    })
    var tb = [{
        iconCls: 'icon-tip',
        text: '专辑详情',
        handler: function () {
            showdetails();
        }
    }, '-', {
        iconCls: 'icon-filesave',
        text: '添加专辑',
        handler: function () {
            $("#insertAlbumDiv").dialog("open")
        }
    }, '-', {
        iconCls: 'icon-filesave',
        text: '添加章节',
        handler: function () {
            var node = $('#tt_album').treegrid("getSelected");
            if (node.albumId == null) {
                if (node._parentId == undefined) {
                    $("#insertChapterDiv").dialog("open")
                    ss = node.id;
                }
            } else {
                alert("请选择要添加进哪个专辑")
            }
        }
    }, '-', {
        iconCls: 'icon-undo',
        text: '下载音频',
        handler: function () {
            var node = $('#tt_album').treegrid("getSelected");
            if (node == null) {
                alert("请选择一个文件")
            } else if (node._parentId == undefined) {
                alert("请选择一个文件")
            } else {
                window.location = "${pageContext.request.contextPath}/album/download.do?title=" + node.title + "&downloadPath=" + node.downloadPath
            }
        }
    }];
    $('#tt_album').treegrid({
        method: 'post',
        url: '${pageContext.request.contextPath}/album/selectAll',
        idField: 'id',
        treeField: 'title',
        toolbar: tb,
        pagination: true,
        fit: true,
        fitColumns: true,
        columns: [[
            {field: 'title', title: '名字', width: 180},
            {field: 'size', title: '章节大小', width: 60},
            {field: 'duration', title: '章节的时长', width: 80}
        ]]
    });

    function showdetails() {
        var status = $("#tt_album").treegrid("getSelected");
        if (status == null) {
            alert("请选择一个专辑");
        } else {
            if (status._parentId == null) {
                $("#detailDialog").dialog("open");
                var id = status.id;
                status = $('#tt_album').treegrid("find", id);
                $("#detailForm").form("load", status);
            } else {
                alert("请选择一个专辑");
            }

        }
    }

    function closeAlbum() {
        $("#insertAlbum").dialog("close");
    }

    //添加专辑
    $("#insertAlbumDiv").dialog({
        title: '添加专辑',
        width: 400,
        height: 200,
        buttons: "#insertAlbumBtn",
        closed: true,
    })

    function doInsertAlbum() {
        $("#insertAlbumForm").form("submit", {
            url: "${pageContext.request.contextPath}/album/addAlbum.do",
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $("#insertAlbumDiv").dialog("close")
                    $('#tt_album').treegrid("reload");
                    $("#atitle").val("");
                    $("#aauthor").val("");
                    $("#aboardcast").val("");
                    $("#abrief").val("");
                } else {
                    alert("添加失败！！");
                }
            }
        })
    }

    //添加章节
    $("#insertChapterDiv").dialog({
        title: '添加章节',
        width: 400,
        height: 200,
        buttons: "#insertChapterBtn",
        closed: true,
    })

    function doInsertChapter() {
        $("#cid").val(ss);
        $("#insertChapterForm").form("submit", {
            url: "${pageContext.request.contextPath}/album/addChapter.do",
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $("#insertChapterDiv").dialog("close")
                    $('#tt_album').treegrid("reload");
                    $("#ctitle").val("");
                }
            }
        })
    }

    //下载

</script>