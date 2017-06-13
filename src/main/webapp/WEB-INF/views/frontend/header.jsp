<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="display: inline-block; width: 100%; height:160px">
	<a href=""><img alt="" src="resources/frontend/images/app/logo.png" style="width: 305px; height: 116px; margin: 22px 22px 22px 10px; display: inline-block;"></a>
	<div style="float: right;">
		<div style="border: 1px solid #e9608d; width: 140px; height: 70px; margin-top: 10px; margin-right:30px;float:right">
			<div style="color: #FFFFFF; font-size: 12pt; background-color: #e9608d; margin: auto; text-align: center; height: 70px; width: 70px; float: left; line-height: 35px;">征稿截止剩余天数</div>
			<div id="countDown" style="color: #e9608d; font-size: 40pt; text-align: center; width: 70px; height: 70px; float: left;"></div>
		</div>

		<div class="zyHeader">
			<ul class="zyMenu">
				<li class="zyItem"><a class="zyLink" href="index" data-page-name="index">首页</a></li>
				<li class="zyItem"><a class="zyLink" href="news/news/1" data-page-name="news">新闻</a></li>
				<li class="zyItem"><a class="zyLink" href="rule/rules" data-page-name="rule">章程</a></li>
				<!--<li class="zyItem"><a class="zyLink" href="judge/judge" data-page-name="judge">评委</a></li>  -->
				<li class="zyItem"><a class="zyLink" href="org" data-page-name="org">组织架构</a></li>
			</ul>
			
			<ul class="zyUserMenu">
				<li  class="zyItem"><span style="color:#6d6d6d;height:50px;font-size:25px;">|</span></li>
				<c:if test="${!empty sessionScope.userId}">
					<li class="zyItem"><a href="production/works" class="zyLink zy20C7BE">${sessionScope.realname}</a></li>
					<li class="zyItem"><a class="zyLink" href="logout">退出</a></li>
				</c:if>
				<c:if test="${empty sessionScope.userId}">
					<li class="zyItem"><a class="zyLink zyIconUser" href="login"></a></li>
				</c:if>
			</ul>

		</div>
	</div>
</div>
<div style="height:1px;background-color:#656464;width:100%"></div>

