
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>

        <%@ include file="head.jsp"%>

        <link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet" >
        <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet" >

        </head>

        <body>

        <c:if test="${object.success != true}">
            ${object.message}
        </c:if>

        <c:if test="${object.success == true}">
            <form id="myForm" class="form-horizontal" method="post" action="#">
            <input type="hidden" name="code" value="${object.object.code}">
            <div class="form-group">
            <label class="control-label col-md-2">邮箱*</label>
            <div class="col-md-8">
            <input type="text" class="form-control" name="email">
            </div>
            </div>

            <div class="form-group">
            <label class="control-label col-md-2">密码*</label>
            <div class="col-md-8">
            <input type="text" class="form-control" name="password" id="password">
            </div>
            </div>

            <div class="form-group">
            <label class="control-label col-md-2">确认密码*</label>
            <div class="col-md-8">
            <input type="text" class="form-control" name="confirmPwd">
            </div>
            </div>

            <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
            <button type="submit" class="btn btn-success form-control">确定</button>
            </div>
            </div>
            </form>
        </c:if>

        <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
        <script src="resources/js/lib/jquery.toastmessage.js"></script>
        <script src="resources/js/lib/jquery.serialize-object.min.js"></script>
        <script src="resources/js/lib/jquery.validate.min.js"></script>
        <script src="resources/js/src/config.js"></script>
        <script src="resources/js/src/setPwd.js"></script>
        </body>
        </html>