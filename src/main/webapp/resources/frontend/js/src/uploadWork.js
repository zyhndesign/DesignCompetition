var uploadWork=(function(config,functions){
    return {
        createUploads:function(){
            for(var i= 1;i<=3;i++){

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
                        uploadContainer:"uploadImagesContainer",
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
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.workDetail.replace(":id",id),{id:id},function(data){

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

    functions.createUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadThumbBtn",
        multiSelection:false,
        multipartParams:{
            fileType:config.uploader.fileType.others
        },
        uploadContainer:"uploadThumbContainer",
        filesAddCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            functions.getImageSize(config.ajaxUrls.imageGet+"?imgPath="+info.object,function(imageSizeMap){
                if(imageSizeMap.width==imageSizeMap.height){
                    $("#thumbUrl").val(config.ajaxUrls.imageGet+"?imgPath="+info.object);

                    $("#thumb").attr("src",config.ajaxUrls.imageGet+"?imgPath="+info.object);

                    $(".error[for='thumbUrl']").remove();
                }else{
                    $().toastmessage("showErrorToast",config.messages.imageSizeError);
                }
            });
        }
    });

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


    $("#myForm").validate({
        ignore:[],
        rules:{
            groupId:{
                required:true
            },
            thumb:{
                required:true
            },
            title:{
                required:true,
                maxlength:32
            },
            image1:{
                required:true
            },
            image2:{
                required:true
            },
            image3:{
                required:true
            },
            introCN:{
                required:true
            },
            introEN:{
                required:true
            },
            attachFile:{
                required:true
            }
        },
        messages:{
            groupId:{
                required:config.validErrors.required
            },
            thumb:{
                required:config.validErrors.required
            },
            title:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            image1:{
                required:config.validErrors.required
            },
            image2:{
                required:config.validErrors.required
            },
            image3:{
                required:config.validErrors.required
            },
            introCN:{
                required:config.validErrors.required
            },
            introEN:{
                required:config.validErrors.required
            },
            attachFile:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            var data={};
            data.pimage=[];
            $(".zyActionPImages").each(function(index,el){
                data.pimage.push($(this).val());
            });
            data.pimage=JSON.stringify(data.pimage);
            data.content="<p>"+$("#introCN").val()+"</p><p>"+$("#introEN").val()+"</p>";
            zyFormHandler.submitFormWithJSON(form,data);
        }
    });
});
