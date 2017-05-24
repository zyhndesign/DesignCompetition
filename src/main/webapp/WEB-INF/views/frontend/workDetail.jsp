
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
                   prefix="fn" %>
    <!DOCTYPE html>
<html>
<head>
        <%@ include file="../head.jsp"%>
        <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >
</head>
<body>
        <%@ include file="header.jsp"%>

        <div class="zyWorkDetail">
            <h3 class="zyTitle">${production.title}</h3>
            <c:set var="group" value="{'1':'产品组','2':'概念组'}"/>
            <span class="zy20C7BE">${group[production.groupId]}</span>
            <span>${production.participantName}</span>
            <p class="zyText">${production.content}</p>
            <c:if test="${!empty production.weblink}">
            <div class="zy20C7BE">网页链接:&nbsp;&nbsp;<a class="zy20C7BE" href="${production.weblink}" target="_blank">${production.weblink}</a></div>
            </c:if>
            <c:if test="${!empty production.attachFile}">
                <div class="zy20C7BE">附件下载:&nbsp;&nbsp;<a class="zy20C7BE" href="${production.attachFile}" target="_blank">${production.attachFile}</a></div>
            </c:if>

            <c:set var="pimage" value="${fn:substring(production.pimage, 1, -1)}" />
            <c:set var="pimageArray" value="${fn:split(pimage, ',')}" />

            <c:foreach items="${pimageArray}" var="i">
                <img src="${i}" style="margin:10px auto;">
            </c:foreach>
        </div>
</body>
</html>