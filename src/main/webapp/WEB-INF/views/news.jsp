<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻</title>
<script type="text/javascript">
	var formData = new FormData();
	formData.append("name",name);
	
	$(document).ready(function(){
		$.ajax({
			url:"",
			type:'POST',
			data:formData,
			processData:false,
			contentType:false,
			beforeSend:function(){
				console.log("正在进行中，请稍后...");
			},
			success:function(responseStr){
				if (responseStr.status === 0){
					console.log("成功"+responseStr);
				}
				else{
					console.log("失败");
				}
			},
			error:function(responseStr){
				console.log("error")
			}
		});
	});
</script>
</head>
<body>

</body>
</html>