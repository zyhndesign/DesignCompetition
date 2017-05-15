
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
    <meta name="keywords" content="xxxx"/>
    <meta name="description" content="xxxx">
    <title>忘记密码</title>
    <base href="http://localhost/competitionStatic/">
    <link href="resources/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet" >
</head>
<body>
    <form id="myForm" class="form-horizontal" method="post" action="#">
        <div class="form-group">
            <label class="control-label col-md-2">邮箱*</label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="email">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
                <button type="submit" class="btn btn-success form-control">确定</button>
            </div>
        </div>
    </form>


    <script>
        var pageName="forgotPwd";
    </script>
    <script src="resources/js/lib/jquery-2.0.3.min.js"></script>
    <script src="resources/js/lib/jquery.toastmessage.js"></script>
    <script src="resources/js/lib/jquery.serialize-object.min.js"></script>
    <script src="resources/js/lib/jquery.validate.min.js"></script>
    <script src="resources/js/src/config.js"></script>
    <script src="resources/js/src/functions.js"></script>
    <script src="resources/js/src/ZYFormHandler.js"></script>
    <script src="resources/js/src/forgotPwd.js"></script>
</body>
</html>