
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

<div class="zyJudgeDetail">
    <div class="zyInfo">
        <img class="zyThumb" src="${object.headicon}">
        <h2 class="zyTitle">${object.name}</h2>
        <div class="zySubTitle">${object.subTitle}</div>
    </div>
    <div class="zyDetail">
        <label class="zyTip">个人简介：</label>
        <div>
            ${object.description}
        </div>
    </div>
</div>

<div class="zyFooter">
    &copy;康乃馨养老产业集团
</div>


        <script>
            var pageName="judge";
        </script>
        <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
        <script src="resources/frontend/js/src/config"></script>
</body>
</html>