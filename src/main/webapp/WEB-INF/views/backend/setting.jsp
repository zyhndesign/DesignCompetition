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
    <title>湖南省老年服务产品设计大赛|阶段设置</title>
    <base href="http://localhost/competitionStatic/">
    <link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
    <link href="resources/backend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>

<script src="views/backend/header.js"></script>

<div class="left">
    <script src="views/backend/menu.js"></script>
</div>

<section class="right">
    <article class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">阶段设置</h1>
            </div>
            <div class="panel-body" id="opt-body">
                <form class="form-horizontal" id="myForm" action="#" method="post">
                    <div class="form-group">
                        <label class="control-label col-md-2">当前阶段</label>
                        <div class="col-md-8">
                            <label class="control-label">作品投递</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">阶段*</label>
                        <div class="col-md-8">
                            <select class="form-control" name="stage">
                                <option value="1">作品投递</option>
                                <option value="2">评审</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-8">
                            <button type="submit" class="btn btn-success form-control">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </article>
</section>

<script src="views/backend/loading.js"></script>


<script>
    var pageName="setting";
</script>
<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
<script src="resources/backend/js/lib/bootstrap.min.js"></script>
<script src="resources/js/src/ZYFormHandler.js"></script>
<script src="resources/backend/js/src/config.js"></script>
<script src="resources/backend/js/src/functions.js"></script>
<script src="resources/backend/js/src/setting.js"></script>

</body>
</html>