
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
        <%@ include file="head.jsp"%>

    <link href="resources/css/src/login.css" type="text/css" rel="stylesheet" >
</head>
<body>
<form class="pCenter" id="myForm" method="post" action="dologin">
   <div class="logo"><span class="glyphicon glyphicon-user"></span></div>
    <h1 class="appTitle">系统登录</h1>
    <div class="row">
        <input class="ctrlInput icon1" type="text" name="username" placeholder="邮箱">
    </div>
    <div class="row">
        <input id="password" class="ctrlInput icon2" type="password" name="password" placeholder="密码">
    </div>

    <div class="row submit">
        <input type="submit" class="ctrlBtn" value="登录">
        <c:if test="${error}">
            <label class="error tCenter">${error}</label>
        </c:if>
    </div>

    
</form>
</body>

<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
        <script src="resources/js/src/config.js"></script>
<script src="resources/js/src/login.js"></script>
</html>
