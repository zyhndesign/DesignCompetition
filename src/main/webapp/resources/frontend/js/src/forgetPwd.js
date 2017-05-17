$(document).ready(function(){
    $("#myForm").validate({
        ignore:[],
        rules:{
            email:{
                required:true,
                email:true
            },
            rand:{
                required:true
            }
        },
        messages:{
            email:{
                required:config.validErrors.required,
                email:config.validErrors.email
            },
            rand:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            var formObj=$(form).serializeObject();
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.forgetPwd,
                type:"get",
                dataType:"json",
                //contentType :"application/json; charset=UTF-8",
                data:formObj,
                success:function(response){
                    if(response.success){
                        $().toastmessage("showSuccessToast",config.messages.emailSend);
                    }else{
                        $().toastmessage("showSuccessToast",response.message);
                    }

                    functions.hideLoading();
                },
                error:function(){
                    $().toastmessage("showSuccessToast",config.messages.networkError);
                }
            });
        }
    });
});
