<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="zyHeader">
	<ul class="zyMenu">
		<li class="zyItem"><a class="zyLink" href="index"
			data-page-name="index">首页</a></li>
		<li class="zyItem"><a class="zyLink" href="news/news/1"
			data-page-name="news">新闻</a></li>
		<li class="zyItem"><a class="zyLink" href="rule/rules"
			data-page-name="rule">章程</a></li>
		<li class="zyItem"><a class="zyLink" href="judge/judge"
			data-page-name="judge">评委</a></li>
	</ul>
	<ul class="zyUserMenu">

		<c:if test="${!empty sessionScope.userId}">
			<li class="zyItem"><a href="production/works"
				class="zyLink zy20C7BE">${sessionScope.realname}</a></li>
			<li class="zyItem"><a class="zyLink" href="logout">退出</a></li>
		</c:if>
		<c:if test="${empty sessionScope.userId}">
			<li class="zyItem"><a class="zyLink zyIconUser" href="login"></a>
			</li>
		</c:if>
	</ul>
</div>