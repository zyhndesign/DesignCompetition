var upload=(function(config,functions){
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
                        multipartParams:null,
                        uploadContainer:"uploadImagesContainer",
                        filesAddCb:null,
                        progressCb:null,
                        uploadedCb:function(info,file,up){
                            $("#imageUrl"+i).val(info.url);

                            $("#image"+i).attr("src",info.url);

                            $(".error[for='imageUrl"+i+"']").remove();
                        }
                    });
                })(i);

            }
        },
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.newsDetail.replace(":id",id),{id:id},function(data){

            });
        }
    }
})(config,functions);

$(document).ready(function(){
    var submitUrl=config.ajaxUrls.newsCreate;

    if(id){
        newsCreate.initData(id);
        submitUrl=config.ajaxUrls.newsUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.newsMgr
    });

    upload.createUploads();

    if(editId){
        upload.initData(editId);
    }

    functions.createUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadThumbBtn",
        multiSelection:false,
        multipartParams:null,
        uploadContainer:"uploadThumbContainer",
        filesAddCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            $("#thumbUrl").val(info.url);

            $("#thumb").attr("src",info.url);

            $(".error[for='thumbUrl]").remove();
        }
    });

    $("#myForm").validate({
        ignore:[],
        rules:{
            type:{
                required:true
            },
            thumb:{
                required:true
            },
            name:{
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
            }
        },
        messages:{
            type:{
                required:config.validErrors.required
            },
            thumb:{
                required:config.validErrors.required
            },
            name:{
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
            }
        },
        submitHandler:function(form) {
            zyFormHandler.submitFormWithPS(form);
        }
    });
});
