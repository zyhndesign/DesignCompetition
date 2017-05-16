
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
        <img class="zyThumb" src="data/images/judge.jpg">
        <h2 class="zyTitle">这里是姓名</h2>
        <h3 class="zySubTitle">这里是人的介绍</h3>
    </div>
    <div class="zyDetail">
        <label class="zyTip">个人简介：</label>
        <p>这里是内容1</p>
        <p>这里是内容2</p>
        <p>这里是内容3</p>
    </div>
</div>

<div class="zyFooter">
    &copy;康乃馨养老产业集团
</div>
</body>
</html>