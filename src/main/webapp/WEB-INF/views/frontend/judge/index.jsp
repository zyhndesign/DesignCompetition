<%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../head.jsp"%>

    <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet" >
    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >
</head>
<body>
    <%@ include file="../header.jsp"%>

    <div class="zyPanel">
        尊敬的评委：xxx您好！
    </div>


<%@ include file="../loading.jsp"%>

<script>
var pageName="judge";
</script>

<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/frontend/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/frontend/js/src/judge/index.js"></script>
</body>
</html>