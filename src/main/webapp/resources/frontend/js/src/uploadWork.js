var uploadWork=(function(config,functions){
    return {
        createUploads:function(){
            for(var i= 1;i<=7;i++){

                //使用立即执行，将i作为参数传入，不然受闭包影响，i总是4
                (function(i){
                    functions.createUploader({
                        maxSize:config.uploader.sizes.img,
                        filter:config.uploader.filters.img,
                        uploadBtn:"uploadImagesBtn"+i,
                        multiSelection:false,
                        multipartParams:{
                            fileType:config.uploader.fileType.productionFile
                        },
                        uploadContainer:"uploadImagesContainer"+i,
                        filesAddCb:null,
                        progressCb:null,
                        uploadedCb:function(info,file,up){

                            $("#imageUrl"+i).val(config.ajaxUrls.imageGet+"?imgPath="+info.object);

                            $("#image"+i).attr("src",config.ajaxUrls.imageGet+"?imgPath="+info.object);

                            $(".error[for='imageUrl"+i+"']").remove();
                        }
                    });
                })(i);

            }
        },
        createThumbUploads:function(){
            for(var i= 1;i<=2;i++){

                //使用立即执行，将i作为参数传入，不然受闭包影响，i总是4
                (function(i){
                    functions.createUploader({
                        maxSize:config.uploader.sizes.img,
                        filter:config.uploader.filters.img,
                        uploadBtn:"uploadThumbBtn"+i,
                        multiSelection:false,
                        multipartParams:{
                            fileType:config.uploader.fileType.productionFile
                        },
                        uploadContainer:"uploadThumbContainer"+i,
                        filesAddCb:null,
                        progressCb:null,
                        uploadedCb:function(info,file,up){

                            functions.getImageSize(config.ajaxUrls.imageGet+"?imgPath="+info.object,function(imageSizeMap){
                                if(imageSizeMap.width==imageSizeMap.height){
                                    $("#thumbUrl"+i).val(config.ajaxUrls.imageGet+"?imgPath="+info.object);

                                    $("#thumb"+i).attr("src",config.ajaxUrls.imageGet+"?imgPath="+info.object);

                                    $(".error[for='thumbUrl"+i+"']").remove();
                                }else{
                                    $().toastmessage("showErrorToast",config.messages.imageSizeError);
                                }
                            });
                        }
                    });
                })(i);

            }
        },
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.workDetail.replace(":id",id),{id:id},function(data){
                var pImages=JSON.parse(data.pimage),
                    content=JSON.parse(data.content);

                $("#attachUrl").val(data.attachFile);
                $("#attach").attr("href",data.attachFile).text(functions.getFileInfo(data.attachFile)["filename"]);

                $("#thumb").attr("src",data.thumb);
                $("#thumbUrl").val(data.thumb);

                $("#introCN").val(content[0]);
                $("#introEN").val(content[1]);

                for(var i= 1;i<=3;i++){
                    $("#imageUrl"+i).val(pImages[i-1]);

                    $("#image"+i).attr("src",pImages[i-1]);
                }

                $("#title").val(data.title);
            });
        },
        getSubmitData:function(){
            var personInfoPanel, workInfoPanel;
            var obj={};
            obj.participantType = $("#selectPersonType input:checked").val();
            personInfoPanel = $(".zyPersonInfoPanel").not(".zyHidden");
            obj.participantName = personInfoPanel.find('input[name="participantName"]').val();
            obj.participantIdNumber = personInfoPanel.find('input[name="participantIdNumber"]').val();
            obj.participantBrief = personInfoPanel.find('textarea[name="participantBrief"]').val();
            obj.teamMember = personInfoPanel.find('textarea[name="teamMember"]').val();

            workInfoPanel = $(".zyWorkInfoPanel").not(".zyHidden");
            obj.groupId = $("#selectGroup input:checked").val();
            obj.category = workInfoPanel.find("input[name='category']").val();
            obj.title = workInfoPanel.find("input[name='title']").val();
            obj.weblink = workInfoPanel.find("input[name='weblink']").val();
            obj.content = workInfoPanel.find("input[name='content']").val();
            obj.thumb = workInfoPanel.find("input[name='thumb']").val();
            obj.pimage=[];
            workInfoPanel.find("input[name='image']").each(function(){
                if($(this).val()){
                    obj.pimage.push($(this).val());
                }
            });
        }
    }
})(config,functions);

$(document).ready(function(){
    var submitUrl=config.ajaxUrls.workCreate;

    if(id){
        uploadWork.initData(id);
        submitUrl=config.ajaxUrls.workUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.works
    });

    uploadWork.createUploads();
    uploadWork.createThumbUploads();

    functions.createUploader({
        maxSize:config.uploader.sizes.zip,
        filter:config.uploader.filters.zip,
        uploadBtn:"uploadAttachBtn",
        multiSelection:false,
        multipartParams:{
            fileType:config.uploader.fileType.attachFile
        },
        uploadContainer:"uploadAttachContainer",
        filesAddCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            $("#attachUrl").val(config.ajaxUrls.imageGet+"?imgPath="+info.object);

            $("#attach").attr("href",config.ajaxUrls.imageGet+"?imgPath="+info.object).text(file.name);

            $(".error[for='attachUrl']").remove();
        }
    });

    $(".zyStep .zyStepItem").click(function(){
        var targetPanel = $(this).data("target");
        $(".zyStepTip").addClass("zyHidden");
        if(targetPanel == "#zyStep1"){
            $("#zyStep1Tip").removeClass("zyHidden");
        }
        if(targetPanel=="#zyStep2"){
            //检测数据
            $("#zyStep2Tip").removeClass("zyHidden");

        }
        if(targetPanel=="#zyPreview"){
            //检测数据，设置数据

        }
        $(".zyStep .zyStepItem.zyActive").removeClass("zyActive");
        $(this).addClass("zyActive");
        $(".zyStepPanel").addClass("zyHidden");
        $(targetPanel).removeClass("zyHidden");

    });

    /***************************填写参赛者信息******************************/
    $("#selectPersonType input[type='radio']").click(function(){
        var targetPanel = $(this).data("target");
        $(".zyPersonInfoPanel").addClass("zyHidden");
        $(targetPanel).removeClass("zyHidden");
    });

    /*****************************作品上传**********************************/
    $("#selectGroup input[type='raido']").click(function(){
        var targetPanel = $(this).data("target");
        $(".zyWorkInfoPanel").addClass("zyHidden");
        $(targetPanel).removeClass("zyHidden");
    });

    $("#submitData").click(function(){
        zyFormHandler.submitFormWithJSON(form,data);
    });
});
