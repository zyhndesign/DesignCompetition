
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="webkit" name="renderer">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="keywords" content="湖南省老年服务产品设计大赛"/>
    <meta name="description" content="湖南省老年服务产品设计大赛">
    <base href="http://localhost/competitionStatic/">
    <title>湖南省老年服务产品设计大赛</title>
    <link href="resources/frontend/css/lib/kkpager_blue.css" type="text/css" rel="stylesheet">
    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >
</head>
<body>
<div class="zyHeader">
    <ul class="zyMenu">
        <li class="zyItem">
            <a class="zyLink">首页</a>
        </li>
        <li class="zyItem">
            <a class="zyLink">新闻</a>
        </li>
        <li class="zyItem">
            <a class="zyLink">章程</a>
        </li>
        <li class="zyItem">
            <a class="zyLink">评委</a>
        </li>
    </ul>
    <ul class="zyUserMenu">
        <li class="zyItem">
            <a class="zyLink zyIconUser">用户</a>
        </li>
    </ul>
</div>

<ul class="zyList1">
    <li class="zyItem">
        <a class="zyLink">
            <span class="zyDate">2019-09-09</span>
            <h3>湖南省老年服务产品设计大赛</h3>
            <p class="zyExcerpt">这里是描述</p>
        </a>
    </li>
    <li class="zyItem">
        <a class="zyLink">
            <span class="zyDate">2019-09-09</span>
            <h3>湖南省老年服务产品设计大赛</h3>
            <p class="zyExcerpt">这里是描述</p>
        </a>
    </li>
    <li class="zyItem">
        <a class="zyLink">
            <span class="zyDate">2019-09-09</span>
            <h3>湖南省老年服务产品设计大赛</h3>
            <p class="zyExcerpt">这里是描述</p>
        </a>
    </li>
    <li class="zyItem">
        <a class="zyLink">
            <span class="zyDate">2019-09-09</span>
            <h3>湖南省老年服务产品设计大赛</h3>
            <p class="zyExcerpt">这里是描述</p>
        </a>
    </li>
    <li class="zyItem">
        <a class="zyLink">
            <span class="zyDate">2019-09-09</span>
            <h3>湖南省老年服务产品设计大赛</h3>
            <p class="zyExcerpt">这里是描述</p>
        </a>
    </li>
</ul>

<div id="kkpager"></div>

<div class="zyFooter">
    &copy;康乃馨养老产业集团
</div>

<script type="text/javascript" src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="resources/frontend/js/lib/kkpager.min.js"></script>

<script>
    function getParameter(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return null;
    }
    $(document).ready(function(){
        var totalPage = 20;
        var totalRecords = 390;
        var pageNo = getParameter('pno');
        if(!pageNo){
            pageNo = 1;
        }
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
            hrefFormer : 'views/frontend/news',
            //链接尾部
            hrefLatter : '.html',

            getLink : function(n){
                return this.hrefFormer + this.hrefLatter + "?pno="+n;
            }
        });
    });
</script>
</body>
</html>