
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <jsp:directive.page import="java.util.Hashtable"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <!DOCTYPE html>
<html>
<head>
        <%@ include file="../head.jsp"%>
        <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >
        <script>
        var pimage="${production.pimage}";
        </script>
</head>
<body>
        <%@ include file="header.jsp"%>

        <div class="zyWorkDetail zyMargin150" id="zyWorkDetail">
            <h3 class="zyTitle">${production.title}</h3>
            <%
         Hashtable table=new Hashtable();
         table.put("1","产品组");
         table.put("2","概念组");
         pageContext.setAttribute("group",table);
        %>
            <span class="zy20C7BE">${group[production.groupId]}</span>
            <span>${production.participantName}</span>
            <p class="zyText">${production.content}</p>
            <c:if test="${!empty production.weblink}">
            <div class="zy20C7BE">网页链接:&nbsp;&nbsp;<a class="zy20C7BE" href="${production.weblink}" target="_blank">${production.weblink}</a></div>
            </c:if>
            <c:if test="${!empty production.attachFile}">
                <div class="zy20C7BE">附件下载:&nbsp;&nbsp;<a class="zy20C7BE" href="${production.attachFile}" target="_blank">${production.attachFile}</a></div>
            </c:if>


        </div>

        <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
        <script>
            $(document).ready(function(){
                var pimageArray=JSON.parse(pimage);
                var pimageHtmlArray=[];
                for(var i= 0,len=pimageArray.length;i<len;i++){
                pimageHtmlArray.push('<img src="'+pimageArray[i]+'" style="margin:10px auto;">');
                }
                $("#zyWorkDetail").append(pimageHtmlArray.join(''));
            });
        </script>
</body>
</html>