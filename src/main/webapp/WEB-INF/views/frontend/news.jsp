
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>

        <%@ include file="../head.jsp"%>

    <link href="resources/frontend/css/lib/kkpager_blue.css" type="text/css" rel="stylesheet">
    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >

        <script>
            var totalCount="${newsPageModel.count}";
            var page="${newsPageModel.currentPage}";
        </script>
</head>
<body>
        <%@ include file="header.jsp"%>

<ul class="zyList1">
    <!--<li class="zyItem">
        <a class="zyLink">
            <span class="zyDate">2019-09-09</span>
            <h3>湖南省老年服务产品设计大赛</h3>
            <p class="zyExcerpt">这里是描述</p>
        </a>
    </li>-->
        <c:forEach items="${newsPageModel.list}" var="item">
            <li class="zyItem">
                <a class="zyLink">
                    <span class="zyDate">${item.publicTime}</span>
                    <h3>${item.title}</h3>
                    <p class="zyExcerpt">${item.newsAbstract}</p>
                </a>
            </li>
        </c:forEach>
</ul>

<div id="kkpager"></div>

<div class="zyFooter">
    &copy;康乃馨养老产业集团
</div>

<script type="text/javascript" src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="resources/frontend/js/lib/kkpager.min.js"></script>

<script>
    $(document).ready(function(){
        var totalPage = totalCount/10;
        var totalRecords = totalCount;
        var pageNo =page;
        //生成分页
        //有些参数是可选的，比如lang，若不传有默认值
        kkpager.generPageHtml({
            pno : pageNo,
            //总页码
            total : totalPage,
            isGoPage: false,
            isShowTotalPage: false,
            isShowCurrPage: false,
            //总数据条数
            totalRecords : totalRecords,
            //链接前部
            hrefFormer : 'news',
            //链接尾部
            hrefLatter : '/',

            getLink : function(n){
                return this.hrefFormer + this.hrefLatter +n;
            }
        });
    });
</script>
</body>
</html>