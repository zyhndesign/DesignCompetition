var judgeCreate=(function(config,functions){
    return{

    }
})(config,functions);

$(document).ready(function(){

    var zyFormHandler=new ZYFormHandler({
        submitUrl:"#",
        redirectUrl:"#"
    });

    functions.createUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadBtn",
        multiSelection:false,
        multipartParams:{
            fileType:4
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
        selector: "#describtion",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#describtion").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='describtion']").remove();
                }
            });
        }
    });
    tinymce.init({
        selector: "#subTitle",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#subTitle").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='subTitle']").remove();
                }
            });
        }
    });

    $("#myForm").validate({
        ignore:[],
        rules:{
            headicon:{
                required:true
            },
            subTitle:{
                required:true
            },
            describtion:{
                required:true
            },
            name:{
                required:true,
                maxlength:32
            }
        },
        messages:{
            headicon:{
                required:config.validErrors.required
            },
            subTitle:{
                required:config.validErrors.required
            },
            describtion:{
                required:config.validErrors.required
            },
            name:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            }
        },
        submitHandler:function(form) {
            zyFormHandler.submitFormWithJSON(form);
        }
    });
});