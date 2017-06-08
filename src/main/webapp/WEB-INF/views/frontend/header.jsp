<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="display: inline-block;width:100%">
	<img alt="" src="resources/frontend/images/app/logo.png" style="width: 234px; height: 90px; margin: 10px 10px 10px 10px; display: inline-block;">
	<div style="border: 1px solid #e9608d; width: 140px; height: 70px;float:right;margin-top:20px">
		<div style="color: #FFFFFF; font-size: 12pt; background-color: #e9608d; margin: auto; padding-top:20px;text-align: center; height: 50px;width:70px;display: inline-block;"><span>征稿截止剩余天数</span></div>
		<div id="countDown" style="color: #e9608d; font-size: 40pt; text-align: center;display: inline; width:70px;height:70px"></div>
	</div>
</div>
<div class="zyHeader">


	<ul class="zyMenu">
		<li class="zyItem"><a class="zyLink" href="index" data-page-name="index">首页</a></li>
		<li class="zyItem"><a class="zyLink" href="news/news/1" data-page-name="news">新闻</a></li>
		<li class="zyItem"><a class="zyLink" href="rule/rules" data-page-name="rule">章程</a></li>
		<li class="zyItem"><a class="zyLink" href="judge/judge" data-page-name="judge">评委</a></li>
	</ul>

	<ul class="zyUserMenu">

		<c:if test="${!empty sessionScope.userId}">
			<li class="zyItem"><a href="production/works" class="zyLink zy20C7BE">${sessionScope.realname}</a></li>
			<li class="zyItem"><a class="zyLink" href="logout">退出</a></li>
		</c:if>
		<c:if test="${empty sessionScope.userId}">
			<li class="zyItem"><a class="zyLink zyIconUser" href="login"></a></li>
		</c:if>
	</ul>

</div>
