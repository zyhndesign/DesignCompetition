var judgeCreate=(function(config,functions){
    return{
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.judgeDetail.replace(":id",id),{id:id},function(data){
                $("#name").val(data.title);
                $("#imageUrl").val(data.headicon);
                $("#image").attr("src",data.headicon);
                $("#subTitle").val(data.subTitle);
                $("#describtion").val(data.describtion);
            });
        }
    }
})(config,functions);

$(document).ready(function(){

    var submitUrl=config.ajaxUrls.judgeCreate;

    if(id){
        newsCreate.initData(id);
        submitUrl=config.ajaxUrls.judgeUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.judgeMgr
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
            functions.getImageSize(config.ajaxUrls.imageGet+"?imgPath="+info.object,function(imageSizeMap){
                if(imageSizeMap.width==imageSizeMap.height){
                    $("#imageUrl").val(config.ajaxUrls.imageGet+"?imgPath="+info.object);

                    $("#image").attr("src",config.ajaxUrls.imageGet+"?imgPath="+info.object);

                    $(".error[for='imageUrl']").remove();
                }else{
                    $().toastmessage("showErrorToast",config.messages.imageSizeError);
                }
            });
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