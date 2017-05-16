var newsCreate=(function(config,functions){
    return{

    }
})(config,functions);

$(document).ready(function(){
    var submitUrl=config.ajaxUrls.newsCreate;
    if(id){
        submitUrl=config.ajaxUrls.newsUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.newsMgr
    });

    functions.createUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadBtn",
        multiSelection:false,
        multipartParams:{
            fileType:100 //1-3后台已经有了，大于3表示others
        },
        uploadContainer:"uploadContainer",
        filesAddedCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            //后台的up-token里面要注明返回图片信息
            if(info.w==500&&info.h==500){
                $("#imageUrl").val(info.url);

                $("#image").attr("src",info.url);

                $(".error[for='imageUrl']").remove();
            }else{
                $().toastmessage("showErrorToast",config.messages.imageSizeError);
            }
        }
    });


    tinymce.init({
        selector: "#content",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#content").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='content']").remove();
                }
            });
        }
    });

    $("#myForm").validate({
        ignore:[],
        rules:{
            image:{
                required:true
            },
            newsAbstract:{
                required:true
            },
            content:{
                required:true
            },
            date:{
                required:true
            },
            title:{
                required:true,
                maxlength:32
            }
        },
        messages:{
            image:{
                required:config.validErrors.required
            },
            newsAbstract:{
                required:config.validErrors.required
            },
            content:{
                required:config.validErrors.required
            },
            date:{
                required:config.validErrors.required
            },
            title:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            }
        },
        submitHandler:function(form) {
            zyFormHandler.submitFormWithJSON(form);
        }
    });
});