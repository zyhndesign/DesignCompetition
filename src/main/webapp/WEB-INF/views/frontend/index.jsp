    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <!DOCTYPE html>
<html>
<head>
        <%@ include file="head.jsp"%>
    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>

        <%@ include file="header.jsp"%>

    <div class="zyTopShow">
        <div class="zyItem">
            <img src="resources/frontend/images/app/topShow.jpg">
            <div class="zyInfo">
                <h2 class="zyTitle">为老设计 康享生活</h2>
                <p class="zyExcerpt">
                    弘扬全名创新精神、促进企事业自主创新能力和核心竞争力，为老年人设计出更科学、更健康、更舒适、
                    更符合需求的涉老产品，进一步提高老年人的生活质量，推动老年服务业的发展。
                </p>
                <a class="zyBtn" href="register">报名参赛</a>
            </div>
        </div>
    </div>
    <img src="resources/frontend/images/app/schedule.jpg">

    <ul class="zyList">
        <!--<li class="zyItem">
            <a class="zyLink">
                <img class="zyThumb" src="data/images/news.png">
                <h3 class="zyTitle">湖南省老年服务产品设计大赛</h3>
                <span class="zyDate">2019-09-09</span>
            </a>
        </li>-->

        <c:forEach items="${newsList}" var="item">
        <li class="zyItem">
        <a class="zyLink" href="news/newsDetail/${item.id}">
        <img class="zyThumb" src="${item.thumb}">
        <h3 class="zyTitle">${item.title}</h3>
        <span class="zyDate">${fn:substring(item.publishTime, 0, 10)}</span>
        </a>
        </li>
        </c:forEach>
    </ul>

    <a class="zyBtn" style="margin: 50px auto;" href="news/news/1">查看更多</a>


    <div class="zyFooter">
        &copy;康乃馨养老产业集团
    </div>


        <script>
        var pageName="index";
        </script>
        <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
        <script src="resources/frontend/js/src/config.js"></script>
</body>
</html>