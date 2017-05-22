
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>

        <%@ include file="../head.jsp"%>


        <link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet" >
        <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet" >
        <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >
</head>
<body>

        <%@ include file="header.jsp"%>
        <%@ include file="pageMenu.jsp"%>

<form id="myForm" class="form-horizontal" method="post" action="#">
    <div class="form-group">
        <label class="control-label col-md-2">姓名*</label>
        <div class="col-md-8">
            <input type="text" class="form-control" name="realname" value="${user.realname}">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">电话*</label>
        <div class="col-md-8">
            <input type="text" class="form-control" name="mobile" value="${user.mobile}">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">地址*</label>
        <div class="col-md-8">
            <input type="text" class="form-control" name="address" value="${user.address}">
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-8">
            <button type="submit" class="btn btn-success form-control">确定</button>
        </div>
    </div>
</form>

<script>
    var pageName="setting";
</script>
<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
        <script src="resources/frontend/js/src/config.js"></script>
        <script src="resources/js/src/functions.js"></script>
        <script src="resources/js/src/ZYFormHandler.js"></script>
<script src="resources/frontend/js/src/resetInfo.js"></script>
</body>
</html>