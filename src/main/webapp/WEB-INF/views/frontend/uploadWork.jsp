
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
    <div class="zyTwoSide">
        <div class="zyCLeft">
        <h4 class="zyTitle">上传作品</h4>
            <div id="zyStepOneTip" class="zyStepTip">
                <p class="zyText">请填写参赛者信息</p>
            </div>
            <div id="zyStepTwoTip" class="zyStepTip">
                <h5 class="zySubTitle">上传要求</h5>
                <p class="zyText">
                    1. 提交3张高精度（<font class="zyFF0000">350dpi以上</font>）产品实物照片，
                    单张图片大小<font class="zyFF0000">不得超过5M</font>。<br>
                    2. 填写产品/服务设计说明（300字以内），并提交
                    相应的辅助资料（如说明书扫描文档，产品/服务网
                    页链接等信息）。<br>
                    3. 缩略图必须为<font class="zyFF0000">正方形</font>，500-600px，图片大小<font class="zyFF0000">不
                    得超过2M</font>。<br>
                    另外，为保证本次大赛评选的公正性，参赛作品及
                    版面上不得出现作者所在单位、姓名（包括英文或
                    拼音缩写）或与作者身份有关的任何图标、图形等
                    个人信息资料。
                </p>
            </div>
        </div>
        <div class="zyCRight">
            <div class="zyStep">
                <div class="zyStepItem">参赛者信息</div>
                >
                <div class="zyStepItem">作品信息</div>
                >
                <div class="zyStepItem">预览提交</div>
            </div>
            <div class="zyContent" id="zyStepOne">
                <div class="zyForm">
                    <div class="zyRow">
                        <input type="radio"><label class="zyFormLabel">个人</label>
                        <input type="radio" style="margin-left:100px"><label class="zyFormLabel">团体</label>
                    </div>
                    <div id="zyPersonalInfo">
                        <div class="zyRow">
                        <label class="zyFormLabel">姓名</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">身份证号</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantIdNumber" class="zyInput">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>
                    </div>
                    <div id="zyTeamInfo">
                        <div class="zyRow">
                        <label class="zyFormLabel">队长姓名</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">队长身份证号</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantIdNumber" class="zyInput">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">队员</label>
                        <div class="zyFormControl">
                        <textarea name="teamMember" class="zyInput zyTextarea">中文逗号隔开</textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>
                    </div>

                    <div id="zyCompanyInfo">
                        <div class="zyRow">
                        <label class="zyFormLabel">名称</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">营业执照号</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>
                    </div>


        </div>
            </div>
            <div class="zyContent" id="zyStepTwo">


            <div class="zyForm">
                <div class="zyRow">
                <input type="radio"><label class="zyFormLabel">产品</label>
                <input type="radio" style="margin-left:100px"><label class="zyFormLabel">概念</label>
                </div>
            </div>

            <div id="zyProductInfo">
                <div class="zyRow">
                <label class="zyFormLabel">类别：</label>
                <div class="zyFormControl zySelect" id="zyCategory">
                <input type="text" class="zyInput">
                <input type="hidden" name="category" class="zyInputValue">
                <span class="zyArrow"></span>
                <ul class="zyOptionList zyHidden">
                <li class="zyOption" data-value="1">生活辅助类</li>
                <li class="zyOption" data-value="2">智能养老类</li>
                <li class="zyOption" data-value="3">综合设计类</li>
                </ul>
                </div>
                <span class="zyRequired">*</span>
                </div>
                <div class="zyRow">
                <label class="zyFormLabel">标题</label>
                <div class="zyFormControl">
                <input type="text" name="title" class="zyInput">
                </div>
                <span class="zyRequired">*</span>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">网址</label>
                <div class="zyFormControl">
                <input type="text" name="participantName" class="zyInput">
                </div>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">简介</label>
                <div class="zyFormControl">
                <textarea name="participantBrief" class="zyInput zyTextarea"></textarea>
                </div>
                <span class="zyRequired">*</span>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">缩略图</label>
                <div class="zyFormControl zyWidth200">
                    <a href="#" class="btn btn-success" id="uploadThumbProductionBtn">
                    <span class="glyphicon glyphicon-upload"></span> 上传
                    </a>
                    <img  id="thumbProduction"  style="width:100px"
                    src="resources/frontend/images/app/defaultThumbImage.jpg"/>
                    <input type="hidden" id="thumbProductionUrl" name="thumb">
                </div>
                <span class="zyRequired">*</span>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">图片1</label>
                <div class="zyFormControl zyWidth200" id="uploadThumbProductionContainer1">
                <a href="#" class="btn btn-success" id="uploadThumbProductionBtn1">
                <span class="glyphicon glyphicon-upload"></span> 上传
                </a>
                <img  id="imageProduction1"  style="width:100px"
                src="resources/frontend/images/app/defaultThumbImage.jpg"/>
                <input type="hidden" id="imageProductionUrl1" name="image">
                </div>
                <span class="zyRequired">*</span>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">图片2</label>
                <div class="zyFormControl zyWidth200" id="uploadImageProductionContainer2">
                <a href="#" class="btn btn-success" id="uploadImageProductionBtn2">
                <span class="glyphicon glyphicon-upload"></span> 上传
                </a>
                <img  id="imageProduction2"  style="width:100px"
                src="resources/frontend/images/app/defaultThumbImage.jpg"/>
                <input type="hidden" id="imageProductionUrl2" name="image">
                </div>
                <span class="zyRequired">*</span>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">图片3</label>
                <div class="zyFormControl zyWidth200" id="uploadImageProductionContainer3">
                <a href="#" class="btn btn-success" id="uploadImageProductionBtn3">
                <span class="glyphicon glyphicon-upload"></span> 上传
                </a>
                <img  id="imageProduction3"  style="width:100px"
                src="resources/frontend/images/app/defaultThumbImage.jpg"/>
                <input type="hidden" id="imageProductionUrl3" name="image">
                </div>
                <span class="zyRequired">*</span>
                </div>

                <div class="zyRow">
                <label class="zyFormLabel">附件</label>
                <div class="zyFormControl zyWidth200" id="uploadAttachProductionContainer1">
                <a href="#" class="btn btn-success" id="uploadAttachProductionBtn1">
                <span class="glyphicon glyphicon-upload"></span> 上传
                </a>
                <a id="attachProducation" style="margin-left:50px;"></a>
                <input type="hidden" id="attachProducationUrl" name="attachFile">
                </div>
                <span class="zyRequired">*</span>
                </div>


        </div>

            <div id="zyConceptInfo">

        <div class="zyRow">
        <label class="zyFormLabel">类别：</label>
        <div class="zyFormControl zySelect" id="zyConceptCategory">
        <input type="text" class="zyInput">
        <input type="hidden" name="category" class="zyInputValue">
        <span class="zyArrow"></span>
        <ul class="zyOptionList zyHidden">
        <li class="zyOption" data-value="1">生活辅助类</li>
        <li class="zyOption" data-value="2">智能养老类</li>
        <li class="zyOption" data-value="3">综合设计类</li>
        </ul>
        </div>
        <span class="zyRequired">*</span>
        </div>
        <div class="zyRow">
        <label class="zyFormLabel">标题</label>
        <div class="zyFormControl">
        <input type="text" name="title" class="zyInput">
        </div>
        <span class="zyRequired">*</span>
        </div>

        <div class="zyRow">
        <label class="zyFormLabel">简介</label>
        <div class="zyFormControl">
        <textarea name="participantBrief" class="zyInput zyTextarea"></textarea>
        </div>
        <span class="zyRequired">*</span>
        </div>

        <div class="zyRow">
        <label class="zyFormLabel">缩略图</label>
        <div class="zyFormControl zyWidth200">
        <a href="#" class="btn btn-success" id="uploadThumbConceptBtn">
        <span class="glyphicon glyphicon-upload"></span> 上传
        </a>
        <img  id="thumbConcept"  style="width:100px"
        src="resources/frontend/images/app/defaultThumbImage.jpg"/>
        <input type="hidden" id="thumbConceptUrl" name="thumb">
        </div>
        <span class="zyRequired">*</span>
        </div>

        <div class="zyRow">
        <label class="zyFormLabel">图片1</label>
        <div class="zyFormControl zyWidth200" id="uploadThumbConceptContainer1">
        <a href="#" class="btn btn-success" id="uploadThumbConceptBtn1">
        <span class="glyphicon glyphicon-upload"></span> 上传
        </a>
        <img  id="imageConcept1"  style="width:100px"
        src="resources/frontend/images/app/defaultThumbImage.jpg"/>
        <input type="hidden" id="imageConceptUrl1" name="image">
        </div>
        <span class="zyRequired">*</span>
        </div>

        <div class="zyRow">
        <label class="zyFormLabel">图片2</label>
        <div class="zyFormControl zyWidth200" id="uploadImageConceptContainer2">
        <a href="#" class="btn btn-success" id="uploadImageConceptBtn2">
        <span class="glyphicon glyphicon-upload"></span> 上传
        </a>
        <img  id="imageConcept2"  style="width:100px"
        src="resources/frontend/images/app/defaultThumbImage.jpg"/>
        <input type="hidden" id="imageConceptUrl2" name="image">
        </div>
        <span class="zyRequired">*</span>
        </div>

        <div class="zyRow">
        <label class="zyFormLabel">图片3</label>
        <div class="zyFormControl zyWidth200" id="uploadImageConceptContainer3">
        <a href="#" class="btn btn-success" id="uploadImageConceptBtn3">
        <span class="glyphicon glyphicon-upload"></span> 上传
        </a>
        <img  id="imageConcept3"  style="width:100px"
        src="resources/frontend/images/app/defaultThumbImage.jpg"/>
        <input type="hidden" id="imageConceptUrl3" name="image">
        </div>
        <span class="zyRequired">*</span>
        </div>


            </div>


            </div>
        </div>
    </div>
    <div class="zyPreviewContainer">
        <div class="zyStep">
        <div class="zyStepItem">参赛者信息</div>
        >
        <div class="zyStepItem">作品信息</div>
        >
        <div class="zyStepItem">预览提交</div>
        </div>

        <div class="zyWorkDetail">

        </div>
    </div>

<form class="form-horizontal" id="myForm" action="#" method="post">
    <div class="form-group">
        <label class="control-label col-md-2">分类*</label>
        <div class="col-md-8">
            <select class="form-control" name="groupId">
                <option value="1">概念组</option>
                <option value="2">产品组</option>
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