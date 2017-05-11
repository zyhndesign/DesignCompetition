<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作品提交</title>
	<script type="text/javascript" src="../resources/js/lib/jquery-2.1.0.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#postBtn").click(function(){
			var formData = new FormData();
			var name = $("#image1").val();
			formData.append("file",$("#image1")[0].files[0]);
			formData.append("name",name);
			formData.append("fileType",2);
			$.ajax({
				url:"/sdx/file/uploadMultiFile",
				type:'POST',
				data:formData,
				processData:false,
				contentType:false,
				beforeSend:function(){
					console.log("正在进行中，请稍后...");
				},
				success:function(responseStr){
					console.log("成功"+responseStr.resultCode);
					console.log("成功"+responseStr.object);
					
				},
				error:function(responseStr){
					console.log("error")
				}
			});
		});
	});
			
	</script>
	
	
</head>
<body>
		<div>
			<input type="file" id="image1"/><button id="postBtn" >提交</button>
		</div>
</body>
</html>