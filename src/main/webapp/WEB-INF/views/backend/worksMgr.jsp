    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="webkit" name="renderer">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="keywords" content="湖南省老年服务产品设计大赛"/>
    <meta name="description" content="湖南省老年服务产品设计大赛">
    <title>湖南省老年服务产品设计大赛|作品管理</title>
    <base href="http://localhost/competitionStatic/">

    <link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="resources/backend/css/lib/jquery.dataTables.css" type="text/css" rel="stylesheet">
    <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
    <link href="resources/backend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>

<script src="views/backend/header.js"></script>

<div class="left">
    <script src="views/backend/menu.js"></script>
</div>

<div class="right">
    <div class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">作品管理</h1>
            </div>
            <div class="panel-body" id="opt-body">

                <div class="row">
                    <div class="input-group col-md-6">
                        <input type="text" id="searchContent" class="form-control" placeholder="名称">
                        <span class="input-group-btn">
                            <button id="searchBtn" class="btn btn-default" type="button">搜索</button>
                        </span>
                    </div>
                </div>

                <table id="myTable" class="dataTable">
                    <thead>
                    <tr>
                        <th></th>
                        <th>名称</th>
                        <th>分类</th>
                        <th>作者</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="views/backend/loading.js"></script>

<script>
    var pageName="works";
</script>
<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/backend/js/lib/bootstrap.min.js"></script>
<script src="resources/backend/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/backend/js/src/config.js"></script>
<script src="resources/backend/js/src/functions.js"></script>
<script src="resources/backend/js/src/ZYTableHandler.js"></script>
<script src="resources/backend/js/src/worksMgr.js"></script>

</body>
</html>
