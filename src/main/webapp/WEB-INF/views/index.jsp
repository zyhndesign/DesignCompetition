<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<shiro:user>  
欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">退出</a>  
</shiro:user> 
<br>
<shiro:hasRole name="竞赛者">  
    用户[<shiro:principal/>]拥有角色竞赛者<br/>  
</shiro:hasRole>
<shiro:hasRole name="管理员">  
    用户[<shiro:principal/>]拥有角色管理员<br/>  
</shiro:hasRole>
<shiro:hasRole name="评委">  
 用户[<shiro:principal/>]拥有角色评委<br/>  
</shiro:hasRole>
<img alt="" src="${pageContext.request.contextPath}/file/image?imgPath=/3/1489045721.png&fileType=2">

</body>
</html>