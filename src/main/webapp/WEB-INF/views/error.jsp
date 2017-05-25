
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>

        <%@ include file="head.jsp"%>
        <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >

        </head>
        <body>

        <%@ include file="frontend/header.jsp"%>

        <img src="resources/images/app/404.png" style="display:block;margin:50px auto;width:auto">
        <div class="zyTCenter">页面访问出错啦！<br>原因可能是访问权限不够或者页面不存在...</div>
        <div class="zyTCenter" style="margin-top:50px;">
            <a class="zyBtn" href="login">重新登陆</a>
        </div>


        </body>
        </html>
