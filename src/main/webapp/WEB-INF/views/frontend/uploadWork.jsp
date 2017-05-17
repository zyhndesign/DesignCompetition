
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>

        <%@ include file="../head.jsp"%>

        <link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet" >
        <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet" >
    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >

        <script>
            var id="";
        </script>
</head>
<body>

<form class="form-horizontal" id="myForm" action="#" method="post">
    <div class="form-group">
        <label class="control-label col-md-2">分类*</label>
        <div class="col-md-8">
            <select class="form-control" name="groupId">
                <option value="1">分类1</option>
                <option value="2">分类2</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">封面图*</label>
        <div class="col-md-10" id="uploadThumbContainer">
            <a href="#" class="btn btn-success" id="uploadThumbBtn">
                <span class="glyphicon glyphicon-upload"></span> 上传
            </a>
            <p class="help-block">请上传1:1的jpg，png</p>
            <img  id="thumb"  style="width:100px"
                  src="resources/frontend/images/app/defaultThumbImage.jpg"/>
            <input type="hidden" id="thumbUrl" name="thumb">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">图片*</label>
        <div class="col-md-10" id="uploadImagesContainer">
            <a href="#" class="btn btn-success" id="uploadImagesBtn1">
                <span class="glyphicon glyphicon-upload"></span> 上传
            </a>
            <img  id="image1"  style="width:100px"
                src="resources/frontend/images/app/defaultThumbImage.jpg"/>
            <input type="hidden" class="zyActionPImages" id="imageUrl1" name="image1">

            <br>
            <a href="#" class="btn btn-success" id="uploadImagesBtn2">
                <span class="glyphicon glyphicon-upload"></span> 上传
            </a>
            <img  id="image2"  style="width:100px"
                src="resources/frontend/images/app/defaultThumbImage.jpg"/>
            <input type="hidden" class="zyActionPImages"  id="imageUrl2" name="image2">

            <br>
            <a href="#" class="btn btn-success" id="uploadImagesBtn3">
                <span class="glyphicon glyphicon-upload"></span> 上传
            </a>
            <img  id="image3"  style="width:100px"
                src="resources/frontend/images/app/defaultThumbImage.jpg"/>
            <input type="hidden" class="zyActionPImages"  id="imageUrl3" name="image3">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">名称*</label>
        <div class="col-md-8">
            <input type="text" class="form-control" name="title" id="title">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">附件*</label>
        <div class="col-md-8" id="uploadAttachContainer">
            <a href="#" class="btn btn-success" id="uploadAttachBtn">
                <span class="glyphicon glyphicon-upload"></span> 上传
            </a>
            <p class="help-block">请上传zip</p>
            <a id="attach" style="margin-left:50px;"></a>
            <input type="hidden" id="attachUrl" name="attachFile">
        </div>
    </div>
    <div class="form-group">
        <label  class="control-label col-md-2">介绍（中文）*</label>
        <div class="col-md-8">
            <textarea class="form-control"  name="introCN" rows="3" id="introCN"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label  class="control-label col-md-2">介绍（英文）*</label>
        <div class="col-md-8">
            <textarea class="form-control"  name="introEN" rows="3" id="introEN"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-8">
            <button type="submit" class="btn btn-success form-control">确定</button>
        </div>
    </div>
</form>

        <%@ include file="loading.jsp"%>

<script>
    var pageName="upload";
</script>

<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/plupload.full.min.js"></script>
<script src="resources/frontend/js/src/config.js"></script>
        <script src="resources/js/src/functions.js"></script>
        <script src="resources/js/src/ZYFormHandler.js"></script>
        <script src="resources/js/src/ZYCOUHandler.js"></script>
<script src="resources/frontend/js/src/uploadWork.js"></script>
</body>
</html>