<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户注册（测试）</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        $(function () {
            //  form 表单提交
            $("#button").click(function () {
                $("#registForm").form("submit", {
                    url: '${pageContext.request.contextPath}/user/regist',
                    dataType: "JSON",
                    success: function (data) {
                        data = JSON.parse(data);
                        console.log(data.flag);
                        if (data.flag) {
                            $("#registForm").form("reset")
                            var goEasy = new GoEasy({
                                appkey: "BC-aa89e6a744874dab84688d9b524dfdae"
                            });
                            goEasy.publish({
                                channel: "my_channel",
                                message: "Hello, GoEasy!"
                            });
                        } else {
                            alert("注册失败")
                        }
                    }
                })
            })
        });
    </script>
</head>
<body>

<div class="login">
    <form id="registForm" enctype="multipart/form-data" method="post">

        <table>
            <tbody>
            <tr>
                <th>
                    姓名:
                </th>
                <td>
                    <input type="text" name="name" class="easyui-validatebox" data-options="required:true"
                           maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    法名:
                </th>
                <td>
                    <input type="text" name="dharma" class="easyui-validatebox" data-options="required:true"
                           maxlength="20" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>
                    性别:
                </th>
                <td>
                    <input type="radio" name="sex" value="1" checked maxlength="20" autocomplete="off"/>男
                    <input type="radio" name="sex" value="0" maxlength="20" autocomplete="off"/>女
                </td>
            </tr>
            <tr>
                <th>
                    地址:
                </th>
                <td>
                    <input style="width: 70px" type="text" name="province" class="easyui-validatebox"
                           data-options="required:true" maxlength="20" autocomplete="off"/>省,
                    <input style="width: 70px" type="text" name="city" class="easyui-validatebox"
                           data-options="required:true" maxlength="20" autocomplete="off"/>市
                </td>
            </tr>
            <tr>
                <th>
                    签名:
                </th>
                <td>
                    <input type="text" name="sign" class="easyui-validatebox" data-options="required:true"
                           maxlength="20" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>
                    电话:
                </th>
                <td>
                    <input type="text" name="phone" class="easyui-validatebox" data-options="required:true"
                           maxlength="20" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>
                    密码:
                </th>
                <td>
                    <input type="password" name="password" class="easyui-validatebox" data-options="required:true"
                           maxlength="20" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>
                    头像:
                </th>
                <td>
                    <input type="file" name="file1" class="easyui-validatebox" data-options="required:true"
                           maxlength="20" autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="">
                    <input type="button" id="button" class="loginButton" value="注册">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>