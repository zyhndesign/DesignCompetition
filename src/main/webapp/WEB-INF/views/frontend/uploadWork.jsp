
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>

        <%@ include file="../head.jsp"%>

        <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet" >
        <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet" >

        <script>
            var id="";
        </script>
</head>
<body>
        <%@ include file="header.jsp"%>

    <div class="zyTwoSide zyPanel" id="zyInfoPanel">
        <div class="zyCLeft zyTipPanel">
            <h4 class="zyTitle">上传作品</h4>
            <div id="zyStep1Tip" class="zyStepTip">
                <p class="zyText">请填写参赛者信息</p>
            </div>
            <div id="zyStep2Tip" class="zyStepTip zyHidden">
                <h5 class="zySubTitle">上传要求</h5>
                <p class="zyText">
                    1. 提交3张高精度（<font class="zyFF0000">350dpi以上</font>）产品实物照片，
                    单张图片大小<font class="zyFF0000">不得超过5M</font>。<br><br>
                    2. 填写产品/服务设计说明（300字以内），并提交
                    相应的辅助资料（如说明书扫描文档，产品/服务网
                    页链接等信息）。<br><br>
                    3. 缩略图必须为<font class="zyFF0000">正方形</font>，500-600px，图片大小<font class="zyFF0000">不得超过2M</font>。<br><br>
                    另外，为保证本次大赛评选的公正性，参赛作品及
                    版面上不得出现作者所在单位、姓名（包括英文或
                    拼音缩写）或与作者身份有关的任何图标、图形等
                    个人信息资料。
                </p>
            </div>
        </div>

        <div class="zyCRight zyHandlerPanel">
            <div class="zyStep" id="zyStep">
                <div class="zyStepItem zyActive" data-target="#zyStep1">参赛者信息</div>
                >
                <div class="zyStepItem" data-target="#zyStep2">作品信息</div>
                >
                <div class="zyStepItem" data-target="#zyPreview">预览提交</div>
            </div>
            <div class="zyStepPanel" id="zyStep1">
                <div class="zyForm">
                    <div class="zyRow" id="selectPersonType">
                        <input type="radio" name="participantType" value="1" checked="checked"
                            data-target="#zyPersonalInfo"><label>个人</label>
                        <input type="radio" name="participantType"  value="2"
                            data-target="#zyTeamInfo" style="margin-left:60px"><label>团体</label>
                        <input type="radio" name="participantType"  value="3"
                            data-target="#zyCompanyInfo" style="margin-left:60px"><label>公司</label>
                    </div>
                    <div id="zyPersonalInfo" class="zyPersonInfoPanel">
                        <div class="zyRow">
                        <label class="zyFormLabel">姓名</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">身份证号</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantIdNumber" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea zyActionRequired"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>
                    </div>
                    <div id="zyTeamInfo" class="zyPersonInfoPanel zyHidden">
                        <div class="zyRow">
                        <label class="zyFormLabel">队长姓名</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">队长身份证号</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantIdNumber" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">队员</label>
                        <div class="zyFormControl">
                        <textarea name="teamMember" class="zyInput zyTextarea zyActionRequired">中文逗号隔开</textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea zyActionRequired"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>
                    </div>

                    <div id="zyCompanyInfo" class="zyPersonInfoPanel zyHidden">
                        <div class="zyRow">
                        <label class="zyFormLabel">名称</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantName" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">营业执照号</label>
                        <div class="zyFormControl">
                        <input type="text" name="participantIdNumber" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea zyActionRequired"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>
                    </div>


                </div>

                <button class="zyBtn zyActionNavBtn" data-target="zyStep2">下一步</button>
            </div>



            <div class="zyStepPanel zyHidden" id="zyStep2">
                <div class="zyForm">
                    <div class="zyRow" id="selectGroup">
                    <input type="radio" name="groupId" value="1" checked="checked" data-target="#zyProductInfo"><label>产品</label>
                    <input type="radio" name="groupId"  value="2" data-target="#zyConceptInfo" style="margin-left:60px"><label>概念</label>
                    </div>

                    <div id="zyProductInfo" class="zyWorkInfoPanel">
                        <div class="zyRow">
                        <label class="zyFormLabel">类别：</label>
                        <div class="zyFormControl zySelect" id="zyCategory">
                        <input type="text" class="zyInput">
                        <input type="hidden" name="category" class="zyInputValue zyActionRequired">
                        <ul class="zyOptionList zyHidden">
                        <li class="zyOption" data-value="">请选择类别</li>
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
                        <input type="text" name="title" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">网址</label>
                        <div class="zyFormControl">
                        <input type="text" name="weblink" class="zyInput">
                        </div>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="content" class="zyInput zyTextarea zyActionRequired"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">缩略图</label>
                        <div class="zyFormControl zyUploadControl" id="uploadThumbContainer1">
                            <a class="zyBtn zyBtnGray" id="uploadThumbBtn1">
                                +&nbsp;上传
                            </a>
                            <img  id="thumb1"  style="width:100px"
                            src="resources/frontend/images/app/defaultThumbImage.jpg"/>
                            <input type="hidden" id="thumbUrl1" name="thumb" class="zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">图片1</label>
                        <div class="zyFormControl zyUploadControl" id="uploadImageContainer1">
                        <a class="zyBtn zyBtnGray" id="uploadImageBtn1">
                        +&nbsp;上传
                        </a>
                        <img  id="image1"  style="width:100px"
                        src="resources/frontend/images/app/defaultImage.jpg"/>
                        <input type="hidden" id="imageUrl1" name="image" class="zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">图片2</label>
                        <div class="zyFormControl zyUploadControl" id="uploadImageContainer2">
                        <a class="zyBtn zyBtnGray" id="uploadImageBtn2">
                        +&nbsp;上传
                        </a>
                        <img  id="image2"  style="width:100px"
                        src="resources/frontend/images/app/defaultImage.jpg"/>
                        <input type="hidden" id="imageUrl2" name="image">
                        </div>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">图片3</label>
                        <div class="zyFormControl zyUploadControl" id="uploadImageContainer3">
                        <a class="zyBtn zyBtnGray" id="uploadImageBtn3">
                        +&nbsp;上传
                        </a>
                        <img  id="image3"  style="width:100px"
                        src="resources/frontend/images/app/defaultImage.jpg"/>
                        <input type="hidden" id="imageUrl3" name="image">
                        </div>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">附件</label>
                        <div class="zyFormControl" id="uploadAttachContainer">
                        <a class="zyBtn zyBtnGray" id="uploadAttachBtn">
                        +&nbsp;上传
                        </a>
                        <a id="attach" class="zy20C7BE" style="margin-left:50px;"></a>
                        <input type="hidden" id="attachUrl" name="attachFile">
                        </div>
                        </div>

                    </div>

                    <div id="zyConceptInfo" class="zyWorkInfoPanel zyHidden">

                        <div class="zyRow">
                        <label class="zyFormLabel">类别：</label>
                        <div class="zyFormControl zySelect" id="zyConceptCategory">
                        <input type="text" class="zyInput">
                        <input type="hidden" name="category" class="zyInputValue zyActionRequired">
                        <ul class="zyOptionList zyHidden">
                        <li class="zyOption" data-value="">请选择类别</li>
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
                        <input type="text" name="title" class="zyInput zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">简介</label>
                        <div class="zyFormControl">
                        <textarea name="participantBrief" class="zyInput zyTextarea zyActionRequired"></textarea>
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">缩略图</label>
                        <div class="zyFormControl zyUploadControl" id="uploadThumbContainer2">
                        <a class="zyBtn zyBtnGray" id="uploadThumbBtn2">
                        +&nbsp;上传
                        </a>
                        <img  id="thumb2"  style="width:100px"
                        src="resources/frontend/images/app/defaultThumbImage.jpg"/>
                        <input type="hidden" id="thumbUrl2" name="thumb" class="zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">图片1</label>
                        <div class="zyFormControl zyUploadControl" id="uploadImageContainer4">
                        <a class="zyBtn zyBtnGray" id="uploadImageBtn4">
                        +&nbsp;上传
                        </a>
                        <img  id="image4"  style="width:100px"
                        src="resources/frontend/images/app/defaultImage.jpg"/>
                        <input type="hidden" id="imageUrl4" name="image" class="zyActionRequired">
                        </div>
                        <span class="zyRequired">*</span>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">图片2</label>
                        <div class="zyFormControl zyUploadControl" id="uploadImageContainer5">
                        <a class="zyBtn zyBtnGray" id="uploadImageBtn5">
                        +&nbsp;上传
                        </a>
                        <img  id="image5"  style="width:100px"
                        src="resources/frontend/images/app/defaultImage.jpg"/>
                        <input type="hidden" id="imageUrl5" name="image">
                        </div>
                        </div>

                        <div class="zyRow">
                        <label class="zyFormLabel">图片3</label>
                        <div class="zyFormControl zyUploadControl" id="uploadImageContainer6">
                        <a class="zyBtn zyBtnGray" id="uploadImageBtn6">
                        +&nbsp;上传
                        </a>
                        <img  id="image6"  style="width:100px"
                        src="resources/frontend/images/app/defaultImage.jpg"/>
                        <input type="hidden" id="imageUrl6" name="image">
                        </div>
                        </div>
                    </div>
                    <button class="zyBtn zyActionNavBtn" data-target="zyStep1">上一步</button>
                    <button class="zyBtn zyActionNavBtn" data-target="zyStep2">下一步</button>
                </div>
            </div>
        </div>
    </div>




    <div class="zyStepPanel zyHidden zyPanel" id="zyPreview">
        <div class="zyStep zyTCenter">
            <div class="zyStepItem" data-target="#zyStep1">参赛者信息</div>
            >
            <div class="zyStepItem" data-target="#zyStep2">作品信息</div>
            >
            <div class="zyStepItem  zyActive"  data-target="#zyPreview">预览提交</div>
        </div>

        <div class="zyWorkDetail">

        </div>

        <div class="zyTCenter">
            <button class="zyBtn zyActionNavBtn" data-target="zyStep2">上一步</button>
            <button class="zyBtn zyActionNavBtn" id="submitData">提交</button>
        </div>
    </div>

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