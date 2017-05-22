
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
        <%@ include file="../head.jsp"%>

    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >
</head>
<body>

        <%@ include file="header.jsp"%>
        <%@ include file="pageMenu.jsp"%>


    <table>
        <thead>
        <tr>
            <th></th>
            <th>名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><img src="resources/images/app/defaultPeopleImage.jpg"></td>
            <td>xxxxxxxx</td>
            <td><a href="#">查看</a>&nbsp;<a href="#">修改</a>&nbsp;<a href="#">删除</a></td>
        </tr>
        </tbody>
    </table>


        <script>
        var pageName="works";
        </script>

        <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
        <script src="resources/js/lib/jquery.toastmessage.js"></script>
        <script src="resources/frontend/js/src/config.js"></script>
        <script src="resources/js/src/functions.js"></script>
        <script src="resources/frontend/js/src/works.js"></script>
</body>
</html>